package com.itau.system.domain.service;

import java.util.List;

import com.itau.system.application.ports.input.UserController;
import com.itau.system.application.ports.output.UserOutputPort;
import com.itau.system.domain.exception.UserNotFoundException;
import com.itau.system.domain.model.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService implements UserController {

    private final UserOutputPort userOutputPort;

	@Override
	public User getById(Long id) {
		System.out.println("get user by ID:");
		return userOutputPort.getById(id).orElseThrow(() -> new UserNotFoundException("user not found with the ID: " + id));
	}

	@Override
	public List<User> getAll() {
		System.out.println("get all users");
		return userOutputPort.getAll();
	}

	@Override
	public User create(User user) {
		System.out.println("New user created with the ID: " + user.getId());
        return userOutputPort.save(user);
	}

	@Override
	public User update(User user) {
		System.out.println("New user created with the ID: " + user.getId());
		return userOutputPort.update(user);
	}

	@Override
	public void delete(Long id) {
		System.out.println("deleted user ID: " + id);
		userOutputPort.delete(id);
	}
}
