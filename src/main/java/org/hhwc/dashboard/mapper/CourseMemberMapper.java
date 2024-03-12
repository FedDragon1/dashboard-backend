package org.hhwc.dashboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.hhwc.dashboard.entity.Course;
import org.hhwc.dashboard.entity.CourseMember;
import org.hhwc.dashboard.entity.Student;

import java.util.List;

public interface CourseMemberMapper extends BaseMapper<CourseMember> {
    @Select("select * from course_member where course_uuid = #{courseUuid}")
    List<CourseMember> selectByCourse(String courseMember);

    @Select("""
            select course.uuid, name, created, instructor_uuid
            from course_member inner join course
            on course.uuid = course_member.course_uuid
            and course_member.student_uuid = #{studentUuid};""")
    List<Course> selectCourseWithStudentId(String studentUuid);

    @Select("""
            select * from student where uuid in
                (select student_uuid from course_member where course_uuid in
                      (select uuid from course where instructor_uuid = #{instructorUuid}))""")
    @Results({
            @Result(column = "uuid", property = "uuid"),
            @Result(column = "name", property = "name"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "uuid", property = "courses", javaType = List.class,
                many = @Many(select = "org.hhwc.dashboard.mapper.CourseMemberMapper.selectCourseWithStudentId")
            )
    })
    List<Student> selectStudentWithInstructor(String instructorUuid);

    @Select("""
           select student_uuid, uuid, grade, name
           from course_member inner join course
               on student_uuid = #{studentUuid} and course.uuid = course_uuid""")
    @Results({
            @Result(column = "student_uuid", property = "studentUuid"),
            @Result(column = "uuid", property = "courseUuid"),
            @Result(column = "grade", property = "grade"),
            @Result(column = "name", property = "courseName")
    })
    List<CourseMember> selectMemberByStudentId(String studentUuid);

    @Select("""
            """)
    List<Student> selectStudentByCourseId(String courseUuid);

    @Update("""
            update course_member set grade=#{grade}
            where student_uuid=#{studentUuid} and course_uuid=#{courseUuid}""")
    int updateById(Double grade, String studentUuid, String courseUuid);

    @Delete("""
            delete from course_member where student_uuid=#{studentUuid} and course_uuid=#{courseUuid}""")
    int deleteById(String studentUuid, String courseUuid);
}
