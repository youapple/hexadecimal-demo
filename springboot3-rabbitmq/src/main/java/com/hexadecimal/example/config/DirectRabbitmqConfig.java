package com.hexadecimal.example.config;

import jakarta.annotation.Resource;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitmqConfig {
    @Resource
    private ConnectionFactory connectionFactory;

    @Bean
    public RabbitAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    /**
     * 队列名称：TestDirectQueue
     * @return
     */
    @Bean
    public Queue TestDirectQueue() {
        // durable: 是否持久化，默认是false。持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在
        // exclusive: 默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete: 是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        // 一般设置一下队列的持久化就好，其余两个就是默认false
        Queue queue = new Queue("TestDirectQueue",true);
        // 声明队列，启动时自动创建队列
        rabbitAdmin().declareQueue(queue);
        return queue;
    }

    /**
     * Direct交换机名称：TestDirectExchange
     * @return
     */
    @Bean
    public DirectExchange TestDirectExchange() {
        return new DirectExchange("TestDirectExchange",true,false);
    }

    /**
     * 将队列和交换机绑定, 并设置匹配路由键：TestDirectRouting
     * @return
     */
    @Bean
    public Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
    }

}