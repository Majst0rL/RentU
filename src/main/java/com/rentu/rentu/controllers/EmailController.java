package com.rentu.rentu.controllers;

import com.rentu.rentu.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emailsender")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/subscribe")
    public void subscribeToNewsletter(@RequestParam String email) {
        // Send a thank you email to the provided email address
        emailService.sendThankYouForSubscription(email);
    }
}
