package org.geekhub.lesson19.user;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    User save(User user);

    void deleteById(int userId);
}
