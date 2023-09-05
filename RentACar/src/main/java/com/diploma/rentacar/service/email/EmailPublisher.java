package com.diploma.rentacar.service.email;

import com.diploma.rentacar.constants.RabbitMQConstants;
import com.diploma.rentacar.dto.email.EmailPropertiesDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.diploma.rentacar.constants.RabbitMQConstants.*;

@Service
@RequiredArgsConstructor
public class EmailPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();



    public  void publishEmail(EmailPropertiesDto emailPropertiesDto) {
        try {
            String json = objectMapper.writeValueAsString(emailPropertiesDto);
            rabbitTemplate.convertAndSend(RabbitMQConstants.QUEUE_RENT_A_CAR_EMAIL_SENDER_PUBLISH, json.getBytes());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
