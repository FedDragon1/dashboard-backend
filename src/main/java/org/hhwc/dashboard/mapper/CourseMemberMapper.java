package org.hhwc.dashboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.hhwc.dashboard.entity.CourseMember;

import java.util.List;

public interface CourseMemberMapper extends BaseMapper<CourseMapper> {
    @Select("select * from course_member where course_uuid = #{courseUuid}")
    List<CourseMember> selectByCourse(String courseMember);
}
