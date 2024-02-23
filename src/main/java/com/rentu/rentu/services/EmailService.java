package com.rentu.rentu.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendThankYouForSubscription(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("taj@ocepek.si");
        message.setSubject("Thank You for Subscribing!");
        message.setText("Thank you for subscribing to our newsletter.");

        emailSender.send(message);
    }
}
