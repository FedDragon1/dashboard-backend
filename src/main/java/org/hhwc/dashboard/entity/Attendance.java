package org.hhwc.dashboard.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class Attendance {
    @TableId
    private String uuid;
    private Date date;
    private String courseUuid;

    @TableField(exist = false)
    private List<AttendanceRecord> records;
}
