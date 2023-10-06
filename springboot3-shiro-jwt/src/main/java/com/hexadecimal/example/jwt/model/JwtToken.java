package com.hexadecimal.example.jwt.model;

import com.hexadecimal.example.jwt.utils.JwtUtil;
import jakarta.annotation.Resource;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * 继承AuthenticationToken，跟AccountRealmh中的doGetAuthenticationInfo的参数类型保持一致
 */
public class JwtToken implements AuthenticationToken {
    @Resource
    private JwtUtil jwtUtil;

    private String username;
    private String token;

    public JwtToken(String token){
        this.token = token;
        this.username = jwtUtil.getClaimFiled(token, "username");
    }

    /**
     * 类似用户名
     * @return
     */
    @Override
    public Object getPrincipal() {
        return username;
    }

    /**
     * 类似密码
     * @return
     */
    @Override
    public Object getCredentials() {
        return token;
    }
}