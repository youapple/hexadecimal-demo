package com.hexadecimal.example.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hexadecimal.example.enums.ResponseCodeEnum;
import com.hexadecimal.example.exception.BaseException;
import com.hexadecimal.example.mapper.UserMapper;
import com.hexadecimal.example.model.Permission;
import com.hexadecimal.example.model.RolePermission;
import com.hexadecimal.example.model.User;
import com.hexadecimal.example.model.UserRole;
import com.hexadecimal.example.req.UserLoginDTO;
import jakarta.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    @Resource
    private UserMapper mapper;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private PermissionService permissionService;

    public void login(UserLoginDTO loginDTO) {
        String username = loginDTO.getUsername();

        // 根据用户名查询用户信息
        User user = super.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username), true);

        if (user != null) {
            // 如果用户被锁定，提前退出
            if (user.getEnabled()) {
                // shiro登录认证
                UsernamePasswordToken token = new UsernamePasswordToken(username, loginDTO.getPassword());
                Subject subject = SecurityUtils.getSubject();

                subject.login(token);
                // 设置session失效时间：永不超时
                subject.getSession().setTimeout(-1001);

                // 修改管理员上一次登录时间
                user.setLastLoginTime(LocalDateTime.now());
                mapper.updateById(user);
            } else {
                throw new BaseException(ResponseCodeEnum.FORBIDDEN, "账号已被锁定，禁止登录！");
            }
        } else {
            throw new BaseException(ResponseCodeEnum.NOT_FOUND, "用户名不存在");
        }
    }

    public List<Permission> getPermissionByUsername(String username) {
        List<Permission> permissions = new ArrayList<>();
        User user = super.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username), true);
        if (null != user) {
            List<UserRole> userRoles = userRoleService.list(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, user.getId()));
            if (CollectionUtils.isNotEmpty(userRoles)) {
                List<Integer> roleIds = new ArrayList<>();
                userRoles.stream().forEach(userRole -> {
                    roleIds.add(userRole.getRoleId());
                });
                List<RolePermission> rolePermissions = rolePermissionService.list(Wrappers.<RolePermission>lambdaQuery().in(RolePermission::getRoleId, roleIds));
                if (CollectionUtils.isNotEmpty(rolePermissions)) {
                    List<Integer> permissionIds = new ArrayList<>();
                    rolePermissions.stream().forEach(rolePermission -> {
                        permissionIds.add(rolePermission.getPermissionId());
                    });
                    permissions = permissionService.list(Wrappers.<Permission>lambdaQuery().in(Permission::getId, permissionIds));
                }
            }
        }
        return permissions;
    }

}