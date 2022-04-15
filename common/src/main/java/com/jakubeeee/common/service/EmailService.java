package com.jakubeeee.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final MailSender mailSender;

    @Value("${spring.mail.username}")
    String SENDER_EMAIL_ADDRESS;

    public void sendMailMessage(SimpleMailMessage message) {
        mailSender.send(message);
    }

    public SimpleMailMessage createMailMessage(String emailAddress, String emailContent, String emailSubject) {
        var mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailAddress);
        mailMessage.setSubject(emailSubject);
        mailMessage.setText(emailContent);
        mailMessage.setFrom(SENDER_EMAIL_ADDRESS);
        return mailMessage;
    }

}
