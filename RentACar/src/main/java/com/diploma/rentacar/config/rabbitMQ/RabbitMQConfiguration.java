package com.diploma.rentacar.config.rabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.diploma.rentacar.constants.RabbitMQConstants.*;


@Configuration
public class RabbitMQConfiguration {

//    @Bean
//    public TopicExchange communicatorPublishingExchange() {
//        return new TopicExchange(EXCHANGE_RENT_A_CAR_EMAIL_SENDER);
//    }
//
//    @Bean
//    public Queue communicatorPublishingQueue() {
//        return new Queue(QUEUE_RENT_A_CAR_EMAIL_SENDER_PUBLISH);
//    }
//
//    @Bean
//    public Binding communicatorPublishingBinding() {
//        return BindingBuilder
//                .bind(communicatorPublishingQueue())
//                .to(communicatorPublishingExchange())
//                .with(RENT_A_CAR_EMAIL_SENDER);
//    }

}


