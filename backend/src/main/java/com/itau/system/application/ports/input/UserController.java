package com.itau.system.application.ports.input;

import java.util.List;

import com.itau.system.domain.model.User;

public interface UserController {
	  
    User getById(Long id);

    List<User> getAll();

    User create(User user);

    User update(User user);

    void delete(Long id);
}
