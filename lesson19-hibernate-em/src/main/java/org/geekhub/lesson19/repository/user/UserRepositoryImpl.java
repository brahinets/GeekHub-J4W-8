package org.geekhub.lesson19.repository.user;

import org.geekhub.lesson19.db.persistence.User;
import org.geekhub.lesson19.repository.GeneralRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class UserRepositoryImpl extends GeneralRepositoryImpl<User, Integer> implements UserRepository {
    UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public Optional<User> findBy(String username) {
        return entityManager.createQuery("SELECT u FROM user u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }
}
