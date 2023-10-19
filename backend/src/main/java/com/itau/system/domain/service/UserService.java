package com.itau.system.domain.service;

import java.util.List;

import com.itau.system.application.ports.input.UserController;
import com.itau.system.application.ports.output.UserOutputPort;
import com.itau.system.domain.exception.UserNotFoundException;
import com.itau.system.domain.model.User;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class UserService implements UserController {

    private final UserOutputPort userOutputPort;

	@Override
	public User getById(Long id) {
		log.info("get user by ID:" + id);
		return userOutputPort.getById(id).orElseThrow(() -> new UserNotFoundException("user not found with the ID: " + id));
	}

	@Override
	public List<User> getAll() {
		log.info("get all users");
		return userOutputPort.getAll();
	}

	@Override
	public User create(User user) {
		log.info("Requesting to create a new user");
		userOutputPort.save(user);
        return user;
	}

	@Override
	public User update(User user) {
		log.info("New user created with the ID: " + user.getId());
		return userOutputPort.update(user);
	}

	@Override
	public void delete(Long id) {
		log.info("deleted user ID: " + id);
		userOutputPort.delete(id);
	}
}
