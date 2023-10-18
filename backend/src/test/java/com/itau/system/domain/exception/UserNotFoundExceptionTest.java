package com.itau.system.domain.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserNotFoundExceptionTest {

    @Test
    void testUserNotFoundExceptionMessage() {
        String errorMessage = "User not found";

        UserNotFoundException exception = new UserNotFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }
}