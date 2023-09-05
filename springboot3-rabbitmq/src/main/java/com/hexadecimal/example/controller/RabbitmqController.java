package com.hexadecimal.example.controller;

import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class RabbitmqController {

    @Resource
    RabbitTemplate rabbitTemplate;

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "Test message, hello world!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值：directRouting，发送到交换机directExchange
        rabbitTemplate.convertAndSend("directExchange", "directRouting", map);
        return "ok";
    }

    @GetMapping("/sendTopicFirstMessage")
    public String sendTopicFirstMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: first";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> firstMap = new HashMap<>();
        firstMap.put("messageId", messageId);
        firstMap.put("messageData", messageData);
        firstMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.first", firstMap);
        return "ok";
    }

    @GetMapping("/sendTopicSecondMessage")
    public String sendTopicSecondMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: second";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> secondMap = new HashMap<>();
        secondMap.put("messageId", messageId);
        secondMap.put("messageData", messageData);
        secondMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.second", secondMap);
        return "ok";
    }

    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: all";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> secondMap = new HashMap<>();
        secondMap.put("messageId", messageId);
        secondMap.put("messageData", messageData);
        secondMap.put("createTime", createTime);
        //fanout模式下routingKey可自定义一个
        rabbitTemplate.convertAndSend("fanoutExchange", "fanout.send", secondMap);
        return "ok";
    }
}
