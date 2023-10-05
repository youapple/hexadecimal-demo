package com.hexadecimal.example.jwt.model;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 继承AuthenticationToken，跟AccountRealmh中的doGetAuthenticationInfo的参数类型保持一致
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token){
        this.token = token;
    }

    /**
     * 类似用户名
     * @return
     */
    @Override
    public Object getPrincipal() {
        return token;
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