package org.hhwc.dashboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.hhwc.dashboard.entity.AttendanceRecord;

import java.util.List;

public interface AttendanceRecordMapper extends BaseMapper<AttendanceRecord> {
    @Select("select * from attendance_record where attendance_uuid = #{attandanceUuid}")
    List<AttendanceRecord> selectByAttendance(String attendanceUuid);
}
