package com.itau.system.infrastructure.adapters.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itau.system.domain.service.UserService;
import com.itau.system.infrastructure.adapters.output.persistence.UserPersistenceAdapter;
import com.itau.system.infrastructure.adapters.output.persistence.mapper.UserMapper;
import com.itau.system.infrastructure.adapters.output.persistence.repository.UserRepository;

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
    public UserPersistenceAdapter userPersistenceAdapter(UserRepository userRepository, UserMapper userMapper) {
        return new UserPersistenceAdapter(userRepository, userMapper);
    }

    @Bean
    public UserService userService(UserPersistenceAdapter userPersistenceAdapter) {
        return new UserService(userPersistenceAdapter);
    }
}
