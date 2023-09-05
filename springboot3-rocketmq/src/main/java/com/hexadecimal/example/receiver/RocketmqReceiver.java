package com.hexadecimal.example.receiver;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "Topic1", consumerGroup = "springboot3_producer_group")
@Slf4j
public class RocketmqReceiver implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("消费者收到的消息: " + message);
    }
}