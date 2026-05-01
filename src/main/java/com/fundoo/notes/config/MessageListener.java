package com.fundoo.notes.config;

import com.fundoo.notes.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageListener {

    private final EmailService emailService;

    @RabbitListener(queues = "user_queue")
    public void receive(String message) {

        System.out.println("Received message: " + message);

        // extract email from message
        String email = message.substring(message.lastIndexOf(" ") + 1);

        // send email
        emailService.sendEmail(email, "Welcome to Fundoo Notes 🚀");
    }
}