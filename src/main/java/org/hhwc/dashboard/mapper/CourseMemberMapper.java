package org.hhwc.dashboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.hhwc.dashboard.entity.Course;
import org.hhwc.dashboard.entity.CourseMember;

import java.util.List;

public interface CourseMemberMapper extends BaseMapper<CourseMapper> {
    @Select("select * from course_member where course_uuid = #{courseUuid}")
    List<CourseMember> selectByCourse(String courseMember);

    @Select("""
            select course.uuid, name, created, instructor_uuid
            from course_member inner join course
            on course.uuid = course_member.course_uuid
            and course_member.student_uuid = #{studentUuid};""")
    List<Course> selectCourseWithStudentId(String studentUuid);
}
