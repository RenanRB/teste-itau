package com.itau.system.infrastructure.adapters.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.system.domain.model.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProducerConfiguration {

    @Value("${topic.name}")
    private String userEmailTopic;

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public ProducerConfiguration(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public String sendMessage(User user) throws JsonProcessingException {
        String userAsMessage = objectMapper.writeValueAsString(user);
        kafkaTemplate.send(userEmailTopic, userAsMessage);

        log.info("user produced {}", userAsMessage);

        return "message sent";
    }
}
