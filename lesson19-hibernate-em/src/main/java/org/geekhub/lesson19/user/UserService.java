package org.geekhub.lesson19.user;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findBy(int userId);

    List<User> findAll();

    User save(User user);

    void deleteById(int userId);
}
