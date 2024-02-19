package org.hhwc.dashboard.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Instructor {
    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;
    private String name;
    private String password;

    @TableField(exist = false)
    private List<Course> courses;
}
