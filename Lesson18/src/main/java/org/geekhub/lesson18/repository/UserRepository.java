package org.geekhub.lesson18.repository;

import org.geekhub.lesson18.dto.User;

public interface UserRepository {
    User findBy(Integer id);
}
