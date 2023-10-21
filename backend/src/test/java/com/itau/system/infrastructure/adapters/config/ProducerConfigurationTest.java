package com.itau.system.infrastructure.adapters.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.system.domain.model.User;
import com.itau.system.infrastructure.kafka.producer.ProducerConfiguration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProducerConfigurationTest {

    private ProducerConfiguration producerConfiguration;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @BeforeEach
    void setUp() {
        producerConfiguration = new ProducerConfiguration(kafkaTemplate, objectMapper);
        ReflectionTestUtils.setField(producerConfiguration, "userEmailTopic", "test-topic");
    }

    @Test
    void sendMessage_ShouldProduceMessage() throws JsonProcessingException {
        User user = User.builder()
        		.id(1L)
                .name("João")
                .surname("Silva")
                .age(30)
                .country("Brasil")
                .build();

        String userAsMessage = "user_message";

        when(objectMapper.writeValueAsString(user)).thenReturn(userAsMessage);

        String result = producerConfiguration.sendMessage(user);

        assertEquals("message sent", result);
        verify(kafkaTemplate, times(1)).send("test-topic", userAsMessage);
    }
}