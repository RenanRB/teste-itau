package com.app.consumer.infrastructure.adapters.input.kafka;

import com.app.consumer.application.input.UserController;
import com.app.consumer.domain.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumerUserAdapter {

	private static final String userEmailTopic = "${topic.name}";
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    private final ObjectMapper objectMapper;
    private final UserController userControler;
    
    @Value("${consumer.timer}")
    private long timerToProcess;
    
    @Value("${consumer.time-unit}")
    private String unitTime;


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
		}, timerToProcess, TimeUnit.valueOf(unitTime));
        
    }
    
    private void processMessage(String message) throws JsonProcessingException {
    	log.info("message consumed {}", message);
        User user = objectMapper.readValue(message, User.class);
        userControler.sendUserConfirmation(user);
    }

}