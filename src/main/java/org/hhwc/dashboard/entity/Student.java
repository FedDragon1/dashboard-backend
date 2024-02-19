package org.hhwc.dashboard.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class Student {
    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;
    private String name;
    private String gender;
    private Date birthday;

    @TableField(exist = false)
    private List<Course> courses;
}
