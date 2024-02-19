package org.hhwc.dashboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.hhwc.dashboard.entity.Course;

import java.util.List;

public interface CourseMapper extends BaseMapper<Course> {

    @Select("select * from course where instructor_uuid = #{instructorUuid}")
    @Results({
            @Result(column = "uuid", property = "uuid"),
            @Result(column = "name", property = "name"),
            @Result(column = "created", property = "created"),
            @Result(column = "instructor_uuid", property = "instructorUuid"),
            @Result(column = "uuid", property = "students", javaType = List.class,
                many = @Many(select = "org.hhwc.dashboard.mapper.CourseMemberMapper.selectByCourse")
            )
    })
    List<Course> selectByInstructor(String instructorUuid);

    @Select("select * from course")
    @Results({
            @Result(column = "uuid", property = "uuid"),
            @Result(column = "name", property = "name"),
            @Result(column = "created", property = "created"),
            @Result(column = "instructor_uuid", property = "instructorUuid"),
            @Result(column = "uuid", property = "students", javaType = List.class,
                    many = @Many(select = "org.hhwc.dashboard.mapper.CourseMemberMapper.selectByCourse")
            )
    })
    List<Course> selectAll();

}
