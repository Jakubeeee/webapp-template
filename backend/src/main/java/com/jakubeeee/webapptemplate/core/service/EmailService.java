package com.jakubeeee.webapptemplate.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private MailSender mailSender;

    @Value("${spring.mail.username}")
    private String SENDER_EMAIL_ADDRESS;

    public void sendMailMessage(SimpleMailMessage message) {
        mailSender.send(message);
    }

    public SimpleMailMessage createMailMessage(String emailAddress, String emailContent, String emailSubject) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailAddress);
        mailMessage.setSubject(emailSubject);
        mailMessage.setText(emailContent);
        mailMessage.setFrom(SENDER_EMAIL_ADDRESS);
        return mailMessage;
    }

}
