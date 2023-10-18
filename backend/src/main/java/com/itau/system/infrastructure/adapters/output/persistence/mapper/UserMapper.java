package com.itau.system.infrastructure.adapters.output.persistence.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.itau.system.domain.model.User;
import com.itau.system.infrastructure.adapters.output.persistence.entity.UserEntity;

public class UserMapper {

    @Autowired
    private ModelMapper mapper;

    public User toUser(UserEntity entity){
        return mapper.map(entity, User.class);
    }
    
    public UserEntity toEntity(User user){
        return mapper.map(user, UserEntity.class);
    }
    
    public List<User> toUserList(List<UserEntity> entityList) {
        return entityList.stream()
            .map(this::toUser)
            .collect(Collectors.toList());
    }

    public List<UserEntity> toEntityList(List<User> userList) {
        return userList.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }

}
