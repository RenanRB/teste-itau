package com.itau.system.infrastructure.adapters.output.persistence;

import java.util.List;
import java.util.Optional;

import com.itau.system.application.ports.output.UserOutputPort;
import com.itau.system.domain.model.User;
import com.itau.system.infrastructure.adapters.output.persistence.entity.UserEntity;
import com.itau.system.infrastructure.adapters.output.persistence.mapper.UserMapper;
import com.itau.system.infrastructure.adapters.output.persistence.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class UserPersistenceAdapter implements UserOutputPort {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public Optional<User> getById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);

        if(userEntity.isEmpty()) {
            return Optional.empty();
        }

        User user = userMapper.toUser(userEntity.get());
    	log.info("get user from db, ID: {}", id);
        return Optional.of(user);
    }

	@Override
	public List<User> getAll() {
		List<UserEntity> allUsers = userRepository.findAll();
    	log.info("get all users from db");
		return userMapper.toUserList(allUsers);
	}

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        userRepository.save(userEntity);
    	log.info("new user created in db, ID: {}", userEntity.getId());
        return userMapper.toUser(userEntity);
    }

	@Override
	public User update(User user) {
		UserEntity userEntity = userMapper.toEntity(user);
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
