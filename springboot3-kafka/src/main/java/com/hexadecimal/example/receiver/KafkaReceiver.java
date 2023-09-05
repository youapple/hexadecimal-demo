package com.hexadecimal.example.receiver;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaReceiver {
    /**
     * 下面的主题是一个数组，可以同时订阅多主题，只需按数组格式即可，也就是用","隔开
     */
    @KafkaListener(topics = {"testTopic"})
    public void receive(ConsumerRecord<?, ?> record){
        log.info("消费者收到的消息key: " + record.key());
        log.info("消费者收到的消息value: " + record.value().toString());
    }
}
