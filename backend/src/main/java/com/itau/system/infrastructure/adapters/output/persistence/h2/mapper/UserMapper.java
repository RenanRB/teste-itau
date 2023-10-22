package com.itau.system.infrastructure.adapters.output.persistence.h2.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.itau.system.domain.model.User;
import com.itau.system.infrastructure.adapters.output.persistence.h2.entity.UserH2Entity;

public class UserMapper {

    @Autowired
    private ModelMapper mapper;

    public User toUser(UserH2Entity entity){
        return mapper.map(entity, User.class);
    }
    
    public UserH2Entity toEntity(User user){
        return mapper.map(user, UserH2Entity.class);
    }
    
    public List<User> toUserList(List<UserH2Entity> entityList) {
        return entityList.stream()
            .map(this::toUser)
            .collect(Collectors.toList());
    }

    public List<UserH2Entity> toEntityList(List<User> userList) {
        return userList.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }

}
