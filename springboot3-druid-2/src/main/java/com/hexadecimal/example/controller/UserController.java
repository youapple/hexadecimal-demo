package com.hexadecimal.example.controller;

import com.hexadecimal.example.mapper.UserMapper;
import com.hexadecimal.example.model.UserDO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    @Resource
    private UserMapper userMapper;

    @GetMapping("query")
    public UserDO query(Long id) {
        UserDO user = userMapper.selectById(id);
        return user;
    }

}