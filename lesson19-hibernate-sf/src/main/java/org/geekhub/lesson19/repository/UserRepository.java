package org.geekhub.lesson19.repository;

import org.geekhub.lesson19.db.persistence.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findBy(Integer id);

    Optional<User> findBy(String username);

    List<User> findAll();

    User save(User user);

    void delete(User user);
}
