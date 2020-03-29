package org.geekhub.lesson19.user;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User save(User user);

    void deleteById(int userId);
}
