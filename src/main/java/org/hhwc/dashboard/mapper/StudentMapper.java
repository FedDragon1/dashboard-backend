package org.hhwc.dashboard.mapper;

import org.apache.ibatis.annotations.Select;
import org.hhwc.dashboard.entity.Student;

import java.util.List;

public interface StudentMapper {

//    @Select("select * from student where ")
    List<Student> selectByCourse(String courseUuid);
}
