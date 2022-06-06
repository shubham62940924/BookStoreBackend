package com.blz.bookstore.util.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class JMSUtil {
    @Autowired
    JavaMailSender javaMailSender;
    public  void sendEmail(String toEmail, String subject, String body) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("ak329222@gmail.com");
        mailMessage.setTo(toEmail);
        mailMessage.setText(body);
        mailMessage.setSubject(subject);
        javaMailSender.send(mailMessage);
        System.out.println(mailMessage);
    }
}
