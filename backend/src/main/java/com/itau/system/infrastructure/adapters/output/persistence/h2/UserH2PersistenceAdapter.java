package com.itau.system.infrastructure.adapters.output.persistence.h2;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;

import com.itau.system.application.ports.output.UserRepository;
import com.itau.system.domain.model.User;
import com.itau.system.infrastructure.adapters.output.persistence.h2.entity.UserH2Entity;
import com.itau.system.infrastructure.adapters.output.persistence.h2.mapper.UserMapper;
import com.itau.system.infrastructure.adapters.output.persistence.h2.repository.UserH2Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Primary
public class UserH2PersistenceAdapter implements UserRepository {

    private final UserH2Repository userRepository;

    private final UserMapper userMapper;

    @Override
    public Optional<User> getById(Long id) {
        Optional<UserH2Entity> userEntity = userRepository.findById(id);

        if(userEntity.isEmpty()) {
            return Optional.empty();
        }

        User user = userMapper.toUser(userEntity.get());
    	log.info("get user from db, ID: {}", id);
        return Optional.of(user);
    }

	@Override
	public List<User> getAll() {
		List<UserH2Entity> allUsers = userRepository.findAll();
    	log.info("get all users from db");
		return userMapper.toUserList(allUsers);
	}

    @Override
    public User save(User user) {
        UserH2Entity userEntity = userMapper.toEntity(user);
        userRepository.save(userEntity);
    	log.info("new user created in db, ID: {}", userEntity.getId());
        return userMapper.toUser(userEntity);
    }

	@Override
	public User update(User user) {
		UserH2Entity userEntity = userMapper.toEntity(user);
		userRepository.save(userEntity);
    	log.info("User updated in db, ID: {}", userEntity.getId());
		return userMapper.toUser(userEntity);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
    	log.info("Success to delete the user from db, ID:{}", id);
	}
}
