package com.itau.system.infrastructure.adapters.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.itau.system.domain.service.UserService;
import com.itau.system.infrastructure.adapters.output.persistence.UserPersistenceAdapter;
import com.itau.system.infrastructure.adapters.output.persistence.mapper.UserMapper;
import com.itau.system.infrastructure.adapters.output.persistence.repository.UserRepository;

@SpringBootTest(properties = "spring.session.stateless=true")
@ExtendWith(MockitoExtension.class)
class BeanConfigurationTest {

    @Test
    void modelMapper_ShouldReturnModelMapper() {
        BeanConfiguration beanConfiguration = new BeanConfiguration();

        ModelMapper modelMapper = beanConfiguration.modelMapper();

        assertNotNull(modelMapper);
    }

    @Test
    void userMapper_ShouldReturnUserMapper() {
        BeanConfiguration beanConfiguration = new BeanConfiguration();

        UserMapper userMapper = beanConfiguration.userMapper();

        assertNotNull(userMapper);
    }

    @Test
    void userPersistenceAdapter_ShouldReturnUserPersistenceAdapter() {
        BeanConfiguration beanConfiguration = new BeanConfiguration();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserMapper userMapper = Mockito.mock(UserMapper.class);

        UserPersistenceAdapter userPersistenceAdapter = beanConfiguration.userPersistenceAdapter(userRepository, userMapper);

        assertNotNull(userPersistenceAdapter);
    }

    @Test
    void userService_ShouldReturnUserService() {
        BeanConfiguration beanConfiguration = new BeanConfiguration();
        UserPersistenceAdapter userPersistenceAdapter = Mockito.mock(UserPersistenceAdapter.class);

        UserService userService = beanConfiguration.userService(userPersistenceAdapter);

        assertNotNull(userService);
    }
}