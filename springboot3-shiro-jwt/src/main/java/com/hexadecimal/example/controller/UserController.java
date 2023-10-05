package com.hexadecimal.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hexadecimal.example.jwt.utils.JwtUtil;
import com.hexadecimal.example.model.User;
import com.hexadecimal.example.req.UserLoginDTO;
import com.hexadecimal.example.res.ResultDTO;
import com.hexadecimal.example.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/user", produces = "application/json;charset=utf-8")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResultDTO<Map<String, String>> login(@RequestBody @Validated UserLoginDTO userLoginDTO, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", userLoginDTO.getUsername()));
        if (user == null) {
            return ResultDTO.error("用户名不存在");
        }

        if (!user.getPassword().equals(userLoginDTO.getPassword())) {
            return ResultDTO.error("用户名或密码错误");
        }

        String token = jwtUtil.generateToken(user.getId());
        response.setHeader(JwtUtil.HEADER, token);
        response.setHeader("Access-control-Expost-Headers", JwtUtil.HEADER);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return ResultDTO.success(map);
    }

    /**
     * 访问该接口就需要有jwt认证
     *
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/logout")
    public ResultDTO<Void> logout() {
        // 退出登录
        SecurityUtils.getSubject().logout();
        return ResultDTO.success();
    }
}
