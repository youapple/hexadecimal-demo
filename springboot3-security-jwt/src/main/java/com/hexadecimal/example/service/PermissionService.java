package com.hexadecimal.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hexadecimal.example.mapper.PermissionMapper;
import com.hexadecimal.example.model.Permission;
import org.springframework.stereotype.Service;

@Service
public class PermissionService extends ServiceImpl<PermissionMapper, Permission> {
}
