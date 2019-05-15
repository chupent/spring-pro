package com.cp.app.core.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName RabbitConfig
 * @Description TODO
 * @createdate 2019/4/29 星期一 18:18
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue Queue(){
        return new Queue("hello");
    }
}
