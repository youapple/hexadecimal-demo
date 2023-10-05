package com.hexadecimal.example.controller;

import com.hexadecimal.example.req.UserLoginDTO;
import com.hexadecimal.example.res.ResultDTO;
import com.hexadecimal.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user", produces = "application/json;charset=utf-8")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResultDTO<Void> login(UserLoginDTO loginDTO) {
        userService.login(loginDTO);

        return ResultDTO.success();
    }

    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public ResultDTO<Void> list() {
        return ResultDTO.success("查询列表成功");
    }

    @RequestMapping(path = "/getById", method = RequestMethod.GET)
    public ResultDTO<Void> getById() {
        return ResultDTO.success("查询详情成功");
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ResultDTO<Void> save() {
        return ResultDTO.success("保存成功");
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public ResultDTO<Void> delete() {
        return ResultDTO.success("删除成功");
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public ResultDTO<Void> update() {
        return ResultDTO.success("修改成功");
    }

}