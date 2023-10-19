package com.itau.system.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.session.stateless=true")
public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .id(1L)
                .name("João")
                .surname("Silva")
                .age(30)
                .country("Brasil")
                .build();
    }

    @Test
    public void testToString() {
        String expectedToString = "User(id=1, name=João, surname=Silva, age=30, country=Brasil)";
        assertEquals(expectedToString, user.toString());
    }

    @Test
    public void testBuilder() {
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("João", user.getName());
        assertEquals("Silva", user.getSurname());
        assertEquals(30, user.getAge());
        assertEquals("Brasil", user.getCountry());
    }

    @Test
    public void testGettersAndSetters() {
        user.setId(2L);
        user.setName("Maria");
        user.setSurname("Ferreira");
        user.setAge(25);
        user.setCountry("Portugal");

        assertEquals(2L, user.getId());
        assertEquals("Maria", user.getName());
        assertEquals("Ferreira", user.getSurname());
        assertEquals(25, user.getAge());
        assertEquals("Portugal", user.getCountry());
    }
}