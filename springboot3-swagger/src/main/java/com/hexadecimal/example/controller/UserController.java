package com.hexadecimal.example.controller;

import com.hexadecimal.example.model.UserDO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "用户管理")
@RestController
@Slf4j
public class UserController {
    @Operation(summary = "查询")
    @GetMapping("query")
    public UserDO query(@Parameter(name = "name", description = "名称") String name) {
        UserDO userDO = new UserDO();
        userDO.setName(name);
        return userDO;
    }

    @Operation(summary = "列表")
    @PostMapping("list")
    public List<UserDO> list() {
        return new ArrayList<UserDO>();
    }

    @Operation(summary ="新增")
    @PostMapping("add")
    public UserDO add(UserDO userDO) {
        return new UserDO();
    }

    @Operation(summary ="修改")
    @PostMapping("update")
    public UserDO update(UserDO userDO) {
        return new UserDO();
    }

    @Operation(summary ="删除")
    @PostMapping("delete")
    public Boolean delete(Integer id) {
        return true;
    }
}