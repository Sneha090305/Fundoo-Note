package com.fundoo.notes.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(String email, String message) {
        System.out.println("Email sent to: " + email);
        System.out.println(" Message: " + message);
    }
}