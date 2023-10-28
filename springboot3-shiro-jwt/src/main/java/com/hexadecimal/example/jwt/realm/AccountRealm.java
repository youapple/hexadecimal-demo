package com.hexadecimal.example.jwt.realm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hexadecimal.example.enums.ResponseCodeEnum;
import com.hexadecimal.example.exception.BaseException;
import com.hexadecimal.example.jwt.model.JwtToken;
import com.hexadecimal.example.jwt.utils.JwtUtil;
import com.hexadecimal.example.model.User;
import com.hexadecimal.example.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Resource
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    /**
     * 多重写一个support
     * 标识这个Realm是专门用来验证JwtToken
     * 不负责验证其他的token（UsernamePasswordToken）
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String jwt = (String) authenticationToken.getCredentials();
        // 获取jwt中关于用户名
        String username = jwtUtil.getClaimsByToken(jwt).getSubject();
        // 查询用户
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (user == null) {
            throw new BaseException(ResponseCodeEnum.BAD_REQUEST, "用户不存在");
        }
        if (!user.getEnabled()) {
            throw new BaseException(ResponseCodeEnum.BAD_REQUEST, "用户被锁定");
        }

        Claims claims = jwtUtil.getClaimsByToken(jwt);
        if (jwtUtil.isTokenExpired(claims.getExpiration())) {
            throw new BaseException(ResponseCodeEnum.BAD_REQUEST, "token过期，请重新登录");
        }
        return new SimpleAuthenticationInfo(user, jwt, getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
