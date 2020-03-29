package org.geekhub.lesson19.userlicense;

import org.geekhub.lesson19.license.License;
import org.geekhub.lesson19.user.User;

import java.util.List;

public interface UserLicenseService {
    List<User> findAllUsers();

    void save(User user, License license);

    void deleteUserById(Integer id);
}
