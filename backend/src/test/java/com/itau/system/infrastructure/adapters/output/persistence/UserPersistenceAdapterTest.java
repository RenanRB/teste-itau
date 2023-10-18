package com.itau.system.infrastructure.adapters.output.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.itau.system.domain.model.User;
import com.itau.system.infrastructure.adapters.output.persistence.entity.UserEntity;
import com.itau.system.infrastructure.adapters.output.persistence.mapper.UserMapper;
import com.itau.system.infrastructure.adapters.output.persistence.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserPersistenceAdapterTest {

    @InjectMocks
    private UserPersistenceAdapter userPersistenceAdapter;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    public void testGetUserById_WhenUserExists() {
        Long userId = 1L;
        UserEntity userEntity = new UserEntity(userId, "João", "Silva", 30, "Brasil");
        User expectedUser = new User(userId, "João", "Silva", 30, "Brasil");

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        Mockito.when(userMapper.toUser(userEntity)).thenReturn(expectedUser);

        Optional<User> user = userPersistenceAdapter.getById(userId);

        assertTrue(user.isPresent());
        assertEquals(expectedUser, user.get());
    }

    @Test
    public void testGetUserById_WhenUserDoesNotExist() {
        Long userId = 1L;

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<User> user = userPersistenceAdapter.getById(userId);

        assertFalse(user.isPresent());
    }

    @Test
    public void testGetAllUsers() {
        List<UserEntity> userEntities = List.of(
            new UserEntity(1L, "João", "Silva", 30, "Brasil"),
            new UserEntity(2L, "Maria", "Santos", 25, "Brasil")
        );

        List<User> expectedUsers = List.of(
            new User(1L, "João", "Silva", 30, "Brasil"),
            new User(2L, "Maria", "Santos", 25, "Brasil")
        );

        Mockito.when(userRepository.findAll()).thenReturn(userEntities);
        Mockito.when(userMapper.toUserList(userEntities)).thenReturn(expectedUsers);

        List<User> users = userPersistenceAdapter.getAll();

        assertEquals(expectedUsers, users);
    }

    @Test
    public void testSaveUser() {
        User userToSave = new User(null, "João", "Silva", 30, "Brasil");
        UserEntity userEntity = new UserEntity(null, "João", "Silva", 30, "Brasil");
        User expectedUser = new User(1L, "João", "Silva", 30, "Brasil");

        Mockito.when(userMapper.toEntity(userToSave)).thenReturn(userEntity);
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);
        Mockito.when(userMapper.toUser(userEntity)).thenReturn(expectedUser);

        User savedUser = userPersistenceAdapter.save(userToSave);

        assertNotNull(savedUser.getId());
        assertEquals(expectedUser, savedUser);
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        User userToUpdate = new User(userId, "João", "Silva", 30, "Brasil");
        UserEntity userEntity = new UserEntity(userId, "João", "Silva", 30, "Brasil");
        User expectedUser = new User(userId, "João", "Silva", 30, "Brasil");

        Mockito.when(userMapper.toEntity(userToUpdate)).thenReturn(userEntity);
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);
        Mockito.when(userMapper.toUser(userEntity)).thenReturn(expectedUser);

        User updatedUser = userPersistenceAdapter.update(userToUpdate);

        assertEquals(expectedUser, updatedUser);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        userPersistenceAdapter.delete(userId);

        Mockito.verify(userRepository).deleteById(userId);
    }
}