package com.hexadecimal.example.realm;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hexadecimal.example.enums.ResponseCodeEnum;
import com.hexadecimal.example.exception.BaseException;
import com.hexadecimal.example.model.Permission;
import com.hexadecimal.example.model.User;
import com.hexadecimal.example.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        // 获取用户名
        String username = token.getUsername();
        // 根据用户名查询用户信息
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username), true);

        if (user == null) {
            throw new BaseException(ResponseCodeEnum.BAD_REQUEST, "登录失败，用户不存在");
        }
        if (user.getEnabled()) {
            String password = new String(token.getPassword());

            if (user.getPassword().equals(password)) {
                return new SimpleAuthenticationInfo(user, password, username);
            }
        }

        if (user.getEnabled()) {
            String password = new String(token.getPassword());

            if (user.getPassword().equals(password)) {
                return new SimpleAuthenticationInfo(user, password, username);
            } else {
                throw new BaseException(ResponseCodeEnum.BAD_REQUEST, "用户名或密码错误，登录失败");
            }
        }

        return null;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        String username = user.getUsername();

        Set<String> permissions = new HashSet<>();
        List<Permission> permissionList =  userService.getPermissionByUsername(username);
        if (CollectionUtils.isNotEmpty(permissionList)) {
            permissionList.stream().forEach(permission -> {
                if (StringUtils.isNotBlank(permission.getUrl())) {
                    permissions.add(permission.getUrl());
                }
            });
        }
        authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }

}