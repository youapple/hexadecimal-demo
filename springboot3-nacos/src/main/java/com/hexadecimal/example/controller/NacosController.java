package com.hexadecimal.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class NacosController {

    @Value("${hexadecimal.name}")
    private String name;

    @GetMapping("/getName")
    public String getName() {
        return name;
    }
}