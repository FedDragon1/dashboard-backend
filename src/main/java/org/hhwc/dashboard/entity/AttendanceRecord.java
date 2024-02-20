package org.hhwc.dashboard.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class AttendanceRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String studentUuid;
    private String attendanceUuid;
}
