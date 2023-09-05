package com.diploma.emailsender.service;

import com.diploma.emailsender.constants.RabbitMQConstants;
import com.diploma.emailsender.dto.EmailPropertiesDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static com.diploma.emailsender.utils.RabbitUtils.ackMessage;
import static com.diploma.emailsender.utils.RabbitUtils.rejectWithDequeMessage;

@Service
@Slf4j
public class EmailSenderListener {

    private final EmailService emailService;

    @Autowired
    public EmailSenderListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitMQConstants.QUEUE_RENT_A_CAR_EMAIL_SENDER_PUBLISH, ackMode = "MANUAL")
    public void receiveMessage(final byte[] message, Channel channel, Long tag) {
        try {
            EmailPropertiesDto emailPropertiesDto = fromMessage(message);
            String emailBody = emailService.generateEmailBody(emailPropertiesDto.getEmailTemplate(), emailPropertiesDto.getPlaceholders());
            emailService.sendEmail(emailBody, emailPropertiesDto);

            ackMessage(channel, tag, false);
        } catch (Exception ex) {
            var errorText = "Something went wrong with publishing template ";
            log.error(errorText, ex);
            rejectWithDequeMessage(channel, tag, false);
        }
    }

    public EmailPropertiesDto fromMessage(byte[] message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String json = new String(message);
            return objectMapper.readValue(json, EmailPropertiesDto.class);
        } catch (Exception e) {
            // Handle exception
            return null;
        }
    }

}
