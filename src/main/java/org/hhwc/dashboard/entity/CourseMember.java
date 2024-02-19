package org.hhwc.dashboard.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CourseMember {
    @TableId
    private String studentUuid, courseUuid;
    private Double grade;
}
