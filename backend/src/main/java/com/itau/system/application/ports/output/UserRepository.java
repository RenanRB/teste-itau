package com.itau.system.application.ports.output;

import java.util.List;
import java.util.Optional;

import com.itau.system.domain.model.User;

public interface UserRepository {

    Optional<User> getById(Long id);

    List<User> getAll();

    User save(User user);

    User update(User user);

    void delete(Long id);
}
