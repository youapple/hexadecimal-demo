package com.hexadecimal.example.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class DirectRabbitmqReceiver {

    /**
     * 消费者监听，绑定交换机、队列、路由键
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    exchange = @Exchange(value = "TestDirectExchange"),
                    value = @Queue(value = "TestDirectQueue"),
                    key = "TestDirectRouting"
            )
    })
    public void receiveMsg(Map message) {
        //接收消息message
        log.info("DirectRabbitmqReceiver消费者收到消息: " + message.toString());
    }

}
