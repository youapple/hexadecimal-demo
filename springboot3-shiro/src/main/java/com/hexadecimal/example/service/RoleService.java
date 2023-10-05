package com.hexadecimal.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hexadecimal.example.mapper.RoleMapper;
import com.hexadecimal.example.model.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {
}
