package com.app.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailService {
	@Autowired
    private JavaMailSender javaMailSender;
	
	public void sendEmail(String sendTo, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sendTo);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
        
        log.info("A new email sent to {}", sendTo);
    }
}
