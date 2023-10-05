package com.hexadecimal.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hexadecimal.example.mapper.UserRoleMapper;
import com.hexadecimal.example.model.UserRole;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> {
}
