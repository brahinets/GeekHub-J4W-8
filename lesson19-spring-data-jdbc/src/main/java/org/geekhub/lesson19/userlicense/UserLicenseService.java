package org.geekhub.lesson19.userlicense;

import org.geekhub.lesson19.license.License;
import org.geekhub.lesson19.user.User;

import java.util.List;
import java.util.Map;

public interface UserLicenseService {
    List<User> findAllUsers();

    Map<Integer, List<License>> findAllLicensesForUsers();

    void createUserWithLicense(String username, String firstName, String lastName);

    void deleteByUserId(int userId);
}
