package org.hhwc.dashboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.hhwc.dashboard.entity.Attendance;

import java.util.List;

public interface AttendanceMapper extends BaseMapper<Attendance> {
    @Select("select * from attendance")
    @Results({
            @Result(column = "uuid", property = "uuid"),
            @Result(column = "date", property = "date"),
            @Result(column = "course_uuid", property = "courseUuid"),
            @Result(column = "uuid", property = "records", javaType = List.class,
                many = @Many(select = "org.hhwc.dashboard.mapper.AttendanceRecordMapper.selectByAttendance")
            )
    })
    List<Attendance> selectAllAttendanceWithName();

    @Select("select * from attendance where course_uuid = #{courseUuid}")
    @Results({
            @Result(column = "uuid", property = "uuid"),
            @Result(column = "date", property = "date"),
            @Result(column = "course_uuid", property = "courseUuid"),
            @Result(column = "uuid", property = "records", javaType = List.class,
                    many = @Many(select = "org.hhwc.dashboard.mapper.AttendanceRecordMapper.selectByAttendance")
            )
    })
    List<Attendance> selectByCourse(String courseUuid);
}
