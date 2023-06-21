package com.hexadecimal.example.service;

import com.hexadecimal.example.api.DemoService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
