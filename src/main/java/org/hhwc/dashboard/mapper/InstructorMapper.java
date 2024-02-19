package org.hhwc.dashboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.hhwc.dashboard.entity.Instructor;

import java.util.List;

public interface InstructorMapper extends BaseMapper<Instructor> {

    @Select("select * from instructor")
    @Results({
            @Result(column = "uuid", property = "uuid"),
            @Result(column = "name", property = "name"),
            @Result(column = "password", property = "password"),
            @Result(column = "uuid", property = "courses", javaType = List.class,
                many = @Many(select = "org.hhwc.dashboard.mapper.CourseMapper.selectByInstructor")
            )
    })
    List<Instructor> selectAllInstructors();

    @Select("select * from instructor where uuid = #{uuid}")
    @Results({
            @Result(column = "uuid", property = "uuid"),
            @Result(column = "name", property = "name"),
            @Result(column = "password", property = "password"),
            @Result(column = "uuid", property = "courses", javaType = List.class,
                    many = @Many(select = "org.hhwc.dashboard.mapper.CourseMapper.selectByInstructor")
            )
    })
    Instructor selectInstructorAndCoursesById(String uuid);
}
