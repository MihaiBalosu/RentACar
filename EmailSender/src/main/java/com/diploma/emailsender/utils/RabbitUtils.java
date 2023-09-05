package com.diploma.emailsender.utils;

import com.rabbitmq.client.Channel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class RabbitUtils {

    public static void rejectWithDequeMessage(Channel channel, Long tag, boolean multiple) {
        try {
            channel.basicNack(tag, multiple, false);
            log.info("Message with tag {} was rejected successfully in multiple mode {}", tag, multiple);
        } catch (Exception ex) {
            log.error("Message with tag {} was not rejected because of exception", tag, ex);
        }
    }

    public static void ackMessage(Channel channel, Long tag, boolean multiple) {
        try {
            channel.basicAck(tag, multiple);
            log.info("Message with tag {} was ack successfully in multiple mode {}", tag, multiple);
        } catch (Exception ex) {
            log.error("Message with tag {} was not ack because of exception", tag, ex);
        }
    }
}

