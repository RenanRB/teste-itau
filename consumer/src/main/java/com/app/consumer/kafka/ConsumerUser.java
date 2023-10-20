package com.app.consumer.kafka;

import com.app.consumer.model.User;
import com.app.consumer.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerUser {

	@Value("${spring.mail.sendTo}")
    private String sendTo;
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    private static final String userEmailTopic = "${topic.name}";

    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    @Autowired
    public ConsumerUser(ObjectMapper objectMapper, EmailService emailService) {
        this.objectMapper = objectMapper;
        this.emailService = emailService;
    }

    @KafkaListener(topics = userEmailTopic)
    public void consumeMessage(String message) throws JsonProcessingException {
    	log.info("A new message was received to consume, and will start in two minutes {}", message);
    	executorService.schedule(() -> {
			try {
				processMessage(message);
			} catch (JsonProcessingException e) {
				log.error("Problem to process the message {}", e);
				e.printStackTrace();
			}
		}, 2, TimeUnit.MINUTES);
        
    }
    
    private void processMessage(String message) throws JsonProcessingException {
    	log.info("message consumed {}", message);
        User user = objectMapper.readValue(message, User.class);
        emailService.sendEmail(sendTo, "New Register Spring boot", "There is a new register to validate: " + user.toString());
    }

}