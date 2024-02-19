package org.hhwc.dashboard.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Admin {
    @TableId
    private String username;
    private String password;
}
