package com.itau.system.infrastructure.adapters.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itau.system.domain.service.UserService;
import com.itau.system.infrastructure.adapters.output.persistence.h2.UserH2PersistenceAdapter;
import com.itau.system.infrastructure.adapters.output.persistence.h2.mapper.UserMapper;
import com.itau.system.infrastructure.adapters.output.persistence.h2.repository.UserH2Repository;

@Configuration
public class BeanConfiguration {
    
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public UserMapper userMapper(){
        return new UserMapper();
    }
    
    @Bean
    public UserH2PersistenceAdapter userPersistenceAdapter(UserH2Repository userRepository, UserMapper userMapper) {
        return new UserH2PersistenceAdapter(userRepository, userMapper);
    }

    @Bean
    public UserService userService(UserH2PersistenceAdapter userPersistenceAdapter) {
        return new UserService(userPersistenceAdapter);
    }
}
