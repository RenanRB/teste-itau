package com.itau.system.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.itau.system.application.ports.output.UserOutputPort;
import com.itau.system.domain.exception.UserNotFoundException;
import com.itau.system.domain.model.User;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserOutputPort userOutputPort;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userOutputPort);
    }

    @Test
    void getById_UserExists_ShouldReturnUser() {
        Long userId = 1L;
        User expectedUser = new User(userId, "João", "Silva", 30, "Brasil");
        Mockito.when(userOutputPort.getById(userId)).thenReturn(Optional.of(expectedUser));

        User result = userService.getById(userId);

        assertEquals(expectedUser, result);
    }

    @Test
    void getById_UserNotFound_ShouldThrowUserNotFoundException() {
        Long userId = 1L;
        Mockito.when(userOutputPort.getById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getById(userId));
    }

    @Test
    void getAll_ShouldReturnListOfUsers() {
        List<User> expectedUsers = List.of(
            new User(1L, "João", "Silva", 30, "Brasil"),
            new User(2L, "Maria", "Santos", 25, "Brasil")
        );
        Mockito.when(userOutputPort.getAll()).thenReturn(expectedUsers);

        List<User> result = userService.getAll();

        assertEquals(expectedUsers, result);
    }

    @Test
    void create_ShouldReturnCreatedUser() {
        User userToCreate = new User(null, "João", "Silva", 30, "Brasil");
        User expectedUser = new User(1L, "João", "Silva", 30, "Brasil");
        Mockito.when(userOutputPort.save(userToCreate)).thenReturn(expectedUser);

        User result = userService.create(userToCreate);

        assertEquals(expectedUser, result);
    }

    @Test
    void update_ShouldReturnUpdatedUser() {
        User userToUpdate = new User(1L, "João", "Silva", 30, "Brasil");
        User expectedUser = new User(1L, "João", "Silva", 30, "Brasil");
        Mockito.when(userOutputPort.update(userToUpdate)).thenReturn(expectedUser);

        User result = userService.update(userToUpdate);

        assertEquals(expectedUser, result);
    }

    @Test
    void delete_ShouldCallUserOutputPortDelete() {
        Long userId = 1L;

        userService.delete(userId);

        Mockito.verify(userOutputPort).delete(userId);
    }
}