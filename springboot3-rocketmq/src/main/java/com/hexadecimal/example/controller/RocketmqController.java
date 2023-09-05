package com.hexadecimal.example.controller;

import com.hexadecimal.example.sender.RocketmqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RocketmqController {
    @Autowired
    private RocketmqSender rocketmqSender;

    @GetMapping("/sendMessage")
    public String sendMessage() {
        rocketmqSender.sendMessage("Hello World");
        return "ok";
    }

    @GetMapping("/sendSpringMessage")
    public String sendSpringMessage() {
        rocketmqSender.sendSpringMessage("Hello World Spring Message");
        return "ok";
    }

    @GetMapping("/sendAsyncMessage")
    public String sendAsyncMessage() {
        rocketmqSender.sendAsyncMessage("Hello World Async Message");
        return "ok";
    }
}
