package com.app.consumer.infrastructure.adapters.input.kafka;

import com.app.consumer.application.input.UserController;
import com.app.consumer.domain.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.verify;

import java.util.concurrent.ScheduledExecutorService;

@SpringBootTest
public class ConsumerUserAdapterTest {

	@Autowired
    private ConsumerUserAdapter consumerUserAdapter;

    @Mock
    private ScheduledExecutorService executorService;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private UserController userController;
 

    @BeforeEach
    void setUp() {
        consumerUserAdapter = new ConsumerUserAdapter(objectMapper, userController);
        ReflectionTestUtils.setField(consumerUserAdapter, "timerToProcess", 1);
        ReflectionTestUtils.setField(consumerUserAdapter, "unitTime", "SECONDS");
    }

    @Test
    void consumeMessage_ShouldProcessMessage() throws JsonProcessingException {
        String message = "test message";
        User user = new User(1L, "João", "Silva", 30, "Brasil");
        Mockito.when(objectMapper.readValue(message, User.class)).thenReturn(user);
        consumerUserAdapter.consumeMessage(message);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        verify(userController).sendUserConfirmation(user);
    }
}
