package com.itau.system.infrastructure.adapters.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.itau.system.domain.service.UserService;
import com.itau.system.infrastructure.adapters.output.persistence.h2.UserH2PersistenceAdapter;
import com.itau.system.infrastructure.adapters.output.persistence.h2.mapper.UserMapper;
import com.itau.system.infrastructure.adapters.output.persistence.h2.repository.UserH2Repository;
import com.itau.system.infrastructure.kafka.producer.ProducerConfiguration;

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
        UserH2Repository userRepository = Mockito.mock(UserH2Repository.class);
        UserMapper userMapper = Mockito.mock(UserMapper.class);
        ProducerConfiguration producer = Mockito.mock(ProducerConfiguration.class);

        UserH2PersistenceAdapter userPersistenceAdapter = beanConfiguration.userPersistenceAdapter(userRepository, userMapper, producer);

        assertNotNull(userPersistenceAdapter);
    }

    @Test
    void userService_ShouldReturnUserService() {
        BeanConfiguration beanConfiguration = new BeanConfiguration();
        UserH2PersistenceAdapter userPersistenceAdapter = Mockito.mock(UserH2PersistenceAdapter.class);

        UserService userService = beanConfiguration.userService(userPersistenceAdapter);

        assertNotNull(userService);
    }
}