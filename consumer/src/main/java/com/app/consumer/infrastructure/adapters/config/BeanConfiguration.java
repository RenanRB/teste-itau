package com.app.consumer.infrastructure.adapters.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import com.app.consumer.domain.service.UserService;
import com.app.consumer.infrastructure.adapters.output.sendInformations.EmailService;

@EnableKafka
@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    @Bean
    public UserService userService(@Value("${spring.mail.sendTo}") String sendTo, EmailService emailService) {
    	return new UserService(sendTo, emailService);
    }

}