package com.diploma.rentacar.constants;

public class RabbitMQConstants {

    public static final String QUEUE_RENT_A_CAR_EMAIL_SENDER_PUBLISH = "rent-a-car.email-sender.publish";

    public static final String EXCHANGE_RENT_A_CAR_EMAIL_SENDER = "rent-a-car.email-sender";

    public static final String  ROUTING_KEY_RENT_A_CAR_EMAIL_SENDER_PUBLISH = QUEUE_RENT_A_CAR_EMAIL_SENDER_PUBLISH;

    public static final String RENT_A_CAR_EMAIL_SENDER = "rent-a-car.email-sender.*";

}
