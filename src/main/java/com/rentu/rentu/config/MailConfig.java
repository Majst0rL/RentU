package com.rentu.rentu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("mail.ocepek.si");
        mailSender.setPort(587);
        mailSender.setUsername("taj@ocepek.si");
        mailSender.setPassword("T3tajocepek");
        return mailSender;
    }
}
