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
public class RabbitmqReceiver {

    /**
     * 消费者监听，绑定交换机、队列、路由键
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    exchange = @Exchange(value = "directExchange"),
                    value = @Queue(value = "directQueue"),
                    key = "directRouting"
            )
    })
    public void receiveDirectMsg(Map message) {
        //接收消息message
        log.info("Direct模式消费者收到消息: " + message.toString());
    }

    /**
     * 消费者监听，绑定队列
     */
    @RabbitListener(queues = "topicFirstQueue")
    public void receiveTopicFirstMsg(Map message) {
        //接收消息message
        log.info("Topic模式(topicFirstQueue)消费者收到消息: " + message.toString());
    }

    /**
     * 消费者监听，绑定队列
     */
    @RabbitListener(queues = "topicSecondQueue")
    public void receiveTopicSecondMsg(Map message) {
        //接收消息message
        log.info("Topic模式(topicSecondQueue)消费者收到消息: " + message.toString());
    }

    /**
     * 消费者监听，绑定队列
     */
    @RabbitListener(queues = "fanoutFirstQueue")
    public void receiveFanoutFirstMsg(Map message) {
        //接收消息message
        log.info("Fanout模式(fanoutFirstQueue)消费者收到消息: " + message.toString());
    }

    /**
     * 消费者监听，绑定队列
     */
    @RabbitListener(queues = "fanoutSecondQueue")
    public void receiveFanoutSecondMsg(Map message) {
        //接收消息message
        log.info("Fanout模式(fanoutSecondQueue)消费者收到消息: " + message.toString());
    }
}
