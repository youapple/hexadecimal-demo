package com.hexadecimal.example.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_user")
public class User implements Serializable {
    private static final long serialVersionUID = -1L;

    private Integer id;
    private String name;
    private String username;
    private String password;
    private String phone;
    private Integer gender;
    private Boolean enabled;
    private LocalDateTime lastLoginTime;

}
