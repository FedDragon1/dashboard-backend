package org.hhwc.dashboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.hhwc.dashboard.entity.Student;

import java.util.List;

public interface StudentMapper extends BaseMapper<Student> {

    @Select("select * from student")
    @Results({
            @Result(column = "uuid", property = "uuid"),
            @Result(column = "name", property = "name"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "uuid", property = "courses", javaType = List.class,
                many = @Many(select = "org.hhwc.dashboard.mapper.CourseMemberMapper.selectCourseWithStudentId")
            )
    })
    List<Student> selectAllStudentsWithCourses();
}
