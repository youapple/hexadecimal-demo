package com.hexadecimal.example.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = -1L;

    private Integer id;
    private Integer roleId;
    private Integer userId;

}