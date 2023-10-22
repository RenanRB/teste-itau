package com.itau.system.infrastructure.adapters.output.persistence.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.itau.system.infrastructure.adapters.output.persistence.h2.entity.UserH2Entity;
import com.itau.system.infrastructure.adapters.output.persistence.h2.repository.UserH2Repository;

@DataJpaTest
public class UserEntityTest {

    @Autowired
    private UserH2Repository userRepository;

    @Test
    public void testSaveAndRetrieveUserEntity() {
        UserH2Entity userEntity = new UserH2Entity();
        userEntity.setName("João");
        userEntity.setSurname("Silva");
        userEntity.setAge(30);
        userEntity.setCountry("Brasil");

        userRepository.save(userEntity);

        Optional<UserH2Entity> retrievedUserEntity = userRepository.findById(userEntity.getId());

        assertTrue(retrievedUserEntity.isPresent());

        UserH2Entity retrievedUser = retrievedUserEntity.get();
        assertEquals("João", retrievedUser.getName());
        assertEquals("Silva", retrievedUser.getSurname());
        assertEquals(30, retrievedUser.getAge());
        assertEquals("Brasil", retrievedUser.getCountry());
    }
    
    @Test
    void testBuilder() {
        UserH2Entity anotherUser = UserH2Entity.builder()
            .id(2L)
            .name("Maria")
            .surname("Santos")
            .age(25)
            .country("Portugal")
            .build();

        assertEquals(2L, anotherUser.getId());
        assertEquals("Maria", anotherUser.getName());
        assertEquals("Santos", anotherUser.getSurname());
        assertEquals(25, anotherUser.getAge());
        assertEquals("Portugal", anotherUser.getCountry());
    }

    @Test
    void testToString() {
    	UserH2Entity userEntity = UserH2Entity.builder()
                .id(1L)
                .name("João")
                .surname("Silva")
                .age(30)
                .country("Brasil")
                .build();
        String expectedToString = "UserH2Entity(id=1, name=João, surname=Silva, age=30, country=Brasil)";
        assertEquals(expectedToString, userEntity.toString());
    }
}