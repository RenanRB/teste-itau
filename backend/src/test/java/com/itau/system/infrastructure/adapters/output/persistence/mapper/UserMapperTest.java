package com.itau.system.infrastructure.adapters.output.persistence.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.itau.system.domain.model.User;
import com.itau.system.infrastructure.adapters.output.persistence.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserMapperTest {

    @InjectMocks
    private UserMapper userMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void toUserList_ShouldMapUserEntityListToUserList() {
        List<UserEntity> entityList = Arrays.asList(
            new UserEntity(1L, "João", "Silva", 30, "Brasil"),
            new UserEntity(2L, "Maria", "Santos", 25, "Brasil")
        );

        when(modelMapper.map(entityList.get(0), User.class))
            .thenReturn(new User(1L, "João", "Silva", 30, "Brasil"));
        when(modelMapper.map(entityList.get(1), User.class))
            .thenReturn(new User(2L, "Maria", "Santos", 25, "Brasil"));

        List<User> userList = userMapper.toUserList(entityList);

        assertEquals(2, userList.size());
        assertEquals(1L, userList.get(0).getId());
        assertEquals("João", userList.get(0).getName());
        assertEquals("Silva", userList.get(0).getSurname());
        assertEquals(30, userList.get(0).getAge());
        assertEquals("Brasil", userList.get(0).getCountry());
        assertEquals(2L, userList.get(1).getId());
        assertEquals("Maria", userList.get(1).getName());
        assertEquals("Santos", userList.get(1).getSurname());
        assertEquals(25, userList.get(1).getAge());
        assertEquals("Brasil", userList.get(1).getCountry());
    }

    @Test
    void toEntityList_ShouldMapUserListToUserEntityList() {
        List<User> userList = Arrays.asList(
            new User(1L, "João", "Silva", 30, "Brasil"),
            new User(2L, "Maria", "Santos", 25, "Brasil")
        );

        when(modelMapper.map(userList.get(0), UserEntity.class))
            .thenReturn(new UserEntity(1L, "João", "Silva", 30, "Brasil"));
        when(modelMapper.map(userList.get(1), UserEntity.class))
            .thenReturn(new UserEntity(2L, "Maria", "Santos", 25, "Brasil"));

        List<UserEntity> entityList = userMapper.toEntityList(userList);

        assertEquals(2, entityList.size());
        assertEquals(1L, entityList.get(0).getId());
        assertEquals("João", entityList.get(0).getName());
        assertEquals("Silva", entityList.get(0).getSurname());
        assertEquals(30, entityList.get(0).getAge());
        assertEquals("Brasil", entityList.get(0).getCountry());
        assertEquals(2L, entityList.get(1).getId());
        assertEquals("Maria", entityList.get(1).getName());
        assertEquals("Santos", entityList.get(1).getSurname());
        assertEquals(25, entityList.get(1).getAge());
        assertEquals("Brasil", entityList.get(1).getCountry());
    }
}