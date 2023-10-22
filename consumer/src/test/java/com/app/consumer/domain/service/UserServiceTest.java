package com.app.consumer.domain.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.consumer.application.output.SendInformations;
import com.app.consumer.domain.model.User;

@SpringBootTest
public class UserServiceTest {

    private UserService userService;

    @Mock
    private SendInformations sendInformations;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService("test@example.com", sendInformations);
    }

    @Test
    public void testSendUserConfirmation() {
        User user = new User(1L, "João", "Silva", 30, "Brasil");

        Mockito.doNothing().when(sendInformations).sendEmail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        userService.sendUserConfirmation(user);

        Mockito.verify(sendInformations, Mockito.times(1))
               .sendEmail("test@example.com", "New Register Spring boot", "There is a new register to validate: " + user.toString());
    }
}