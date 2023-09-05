package com.hexadecimal.example.sender;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RocketmqSender {
    /**
     * 注入RocketMQTemplate
     */
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送普通消息
     *
     * @param message
     * @return
     */
    public boolean sendMessage(String message) {
        rocketMQTemplate.convertAndSend("Topic1:TagA", message);
        return true;
    }

    /**
     * 发送Spring的消息
     *
     * @param message
     * @return
     */
    public boolean sendSpringMessage(String message) {
        rocketMQTemplate.send("Topic1:TagA", MessageBuilder.withPayload(message).build());
        return true;
    }

    /**
     * 发送异步消息
     *
     * @param message
     * @return
     */
    public boolean sendAsyncMessage(String message) {
        //发送异步消息
        rocketMQTemplate.asyncSend("Topic1:TagA", message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                //发送成功
                return;
            }

            @Override
            public void onException(Throwable throwable) {
                //发送失败
                return;
            }
        });
        return true;
    }

    /**
     * 发送顺序消息
     * 注：需要加上synchronized，消费者多线程下会不保证顺序
     *
     * @param list
     * @return
     */
    public synchronized boolean sendAscMessage(List<String> list) {
        for (String str : list) {
            //发送顺序消息
            rocketMQTemplate.syncSendOrderly("Topic1", str, str + "hash");
        }
        return true;
    }

}
