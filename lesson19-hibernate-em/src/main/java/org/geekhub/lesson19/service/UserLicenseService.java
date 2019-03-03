package org.geekhub.lesson19.service;

import org.geekhub.lesson19.db.persistence.License;
import org.geekhub.lesson19.db.persistence.User;

public interface UserLicenseService {
    void save(User user, License license);

    void delete(User user);
}
