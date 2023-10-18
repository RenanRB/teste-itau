package com.itau.system.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTest {

    @Test
    void testUserConstructorAndGetters() {
        Long id = 1L;
        String name = "João";
        String surname = "Silva";
        int age = 30;
        String country = "Brasil";

        User user = new User(id, name, surname, age, country);

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(surname, user.getSurname());
        assertEquals(age, user.getAge());
        assertEquals(country, user.getCountry());
    }

    @Test
    void testUserSetters() {
        User user = new User();

        user.setId(1L);
        user.setName("João");
        user.setSurname("Silva");
        user.setAge(30);
        user.setCountry("Brasil");

        assertEquals(1L, user.getId());
        assertEquals("João", user.getName());
        assertEquals("Silva", user.getSurname());
        assertEquals(30, user.getAge());
        assertEquals("Brasil", user.getCountry());
    }
}