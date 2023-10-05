package com.hexadecimal.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hexadecimal.example.mapper.RolePermissionMapper;
import com.hexadecimal.example.model.RolePermission;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionService extends ServiceImpl<RolePermissionMapper, RolePermission> {
}
