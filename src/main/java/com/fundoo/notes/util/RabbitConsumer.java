package com.fundoo.notes.util;

import com.fundoo.notes.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consume(String message) {

        log.info(" Received message from queue: {}", message);

        // simulate email / notification
    }
}