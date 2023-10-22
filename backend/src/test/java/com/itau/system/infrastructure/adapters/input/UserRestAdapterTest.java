package com.itau.system.infrastructure.adapters.input;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.system.application.ports.input.UserController;
import com.itau.system.domain.model.User;

@SpringBootTest(properties = "spring.session.stateless=true")
@ExtendWith(MockitoExtension.class)
class UserRestAdapterTest {

    @Mock
    private UserController userController;

    private UserRestAdapter userRestAdapter;

    @BeforeEach
    void setUp() {
        userRestAdapter = new UserRestAdapter(userController, new ModelMapper());
    }

    @Test
    void getUser_ShouldReturnUserWithStatusOK() {
        Long userId = 1L;
        User expectedUser = new User(userId, "João", "Silva", 30, "Brasil");
        Mockito.when(userController.getById(userId)).thenReturn(expectedUser);

        ResponseEntity<User> response = userRestAdapter.getUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUser, response.getBody());
    }

    @Test
    void getAll_ShouldReturnAllUsersWithStatusOK() {
        List<User> expectedUsers = Arrays.asList(
                new User(1L, "João", "Silva", 30, "Brasil"),
                new User(2L, "Maria", "Santos", 25, "Brasil")
        );
        Mockito.when(userController.getAll()).thenReturn(expectedUsers);

        ResponseEntity<List<User>> response = userRestAdapter.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUsers, response.getBody());
    }

    @Test
    void createUser_ShouldReturnCreatedUserWithStatusCreated() throws JsonProcessingException {
        User userToCreate = new User(null, "João", "Silva", 30, "Brasil");
        User expectedUser = new User(1L, "João", "Silva", 30, "Brasil");
        Mockito.when(userController.create(userToCreate)).thenReturn(expectedUser);

        ResponseEntity<User> response = userRestAdapter.createUser(userToCreate);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedUser, response.getBody());
    }

    @Test
    void updateUser_ShouldReturnUpdatedUserWithStatusOK() {
        User userToUpdate = new User(1L, "João", "Silva", 30, "Brasil");
        User expectedUser = new User(1L, "João", "Silva", 30, "Brasil");
        Mockito.when(userController.update(userToUpdate)).thenReturn(expectedUser);

        ResponseEntity<User> response = userRestAdapter.updateUser(userToUpdate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUser, response.getBody());
    }

    @Test
    void deleteUser_ShouldReturnNoContent() {
        Long userId = 1L;

        ResponseEntity<User> response = userRestAdapter.deleteUser(userId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}