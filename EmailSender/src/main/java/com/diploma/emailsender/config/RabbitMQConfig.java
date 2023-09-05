package com.diploma.emailsender.config;

import com.diploma.emailsender.constants.RabbitMQConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue myQueue() {
        return new Queue(RabbitMQConstants.QUEUE_RENT_A_CAR_EMAIL_SENDER_PUBLISH);
    }
}
