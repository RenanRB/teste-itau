package com.itau.system.infrastructure.adapters.output.persistence.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.itau.system.infrastructure.adapters.output.persistence.repository.UserRepository;

@DataJpaTest
public class UserEntityTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndRetrieveUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("João");
        userEntity.setSurname("Silva");
        userEntity.setAge(30);
        userEntity.setCountry("Brasil");

        userRepository.save(userEntity);

        Optional<UserEntity> retrievedUserEntity = userRepository.findById(userEntity.getId());

        assertTrue(retrievedUserEntity.isPresent());

        UserEntity retrievedUser = retrievedUserEntity.get();
        assertEquals("João", retrievedUser.getName());
        assertEquals("Silva", retrievedUser.getSurname());
        assertEquals(30, retrievedUser.getAge());
        assertEquals("Brasil", retrievedUser.getCountry());
    }
}