package org.geekhub.lesson20.service;

import org.geekhub.lesson20.db.persistence.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findBy(Integer id);

    Optional<User> findBy(String username);

    List<User> findAll();

    void save(User user);
}
