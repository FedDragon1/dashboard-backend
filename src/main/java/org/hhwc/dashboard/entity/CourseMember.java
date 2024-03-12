package org.hhwc.dashboard.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CourseMember {
    private String studentUuid;
    private String courseUuid;
    private Double grade;

    @TableField(exist = false)
    private String courseName;
}
