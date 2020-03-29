package org.geekhub.lesson19.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface UserRepository extends CrudRepository<User, Integer> {
    @Override
    List<User> findAll();
}
