package org.geekhub.lesson19.repository.user;

import org.geekhub.lesson19.db.persistence.User;
import org.geekhub.lesson19.repository.GeneralRepository;

import java.util.Optional;

public interface UserRepository extends GeneralRepository<User, Integer> {
    Optional<User> findBy(String username);
}
