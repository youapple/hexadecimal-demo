package com.hexadecimal.example.jwt.userdetails;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hexadecimal.example.model.AccountUser;
import com.hexadecimal.example.model.Permission;
import com.hexadecimal.example.model.User;
import com.hexadecimal.example.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username), true);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return new AccountUser(user.getId(), user.getUsername(), user.getPassword(), getUserAuthority(user.getUsername()));
    }

    /**
     * 获取用户权限信息（角色、菜单权限）
     * @param username
     * @return
     */
    public List<GrantedAuthority> getUserAuthority(String username) {
        // 角色(比如ROLE_admin)，菜单操作权限(比如sys:user:list)
        // 角色必须以ROLE_开头，security在判断角色时会自动截取ROLE_
        List<Permission> permissions = userService.getPermissionByUsername(username);
        // 比如ROLE_admin,ROLE_normal,sys:user:list,...
        String authority = "";
        if (CollectionUtils.isNotEmpty(permissions)) {
            List<String> urls = permissions.stream().map(Permission::getUrl).collect(Collectors.toList());
            authority = StrUtil.join(",", urls);
        }
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }
}
