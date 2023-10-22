package com.app.consumer.infrastructure.adapters.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.consumer.domain.service.UserService;
import com.app.consumer.infrastructure.adapters.output.sendInformations.EmailService;

@SpringBootTest
public class BeanConfigurationTest {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Test
    public void testModelMapperBean() {
        assertNotNull(modelMapper);
    }

    @Test
    public void testUserServiceBean() {
        assertNotNull(userService);
        assertNotNull(emailService);
    }

}