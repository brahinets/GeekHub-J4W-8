package org.geekhub.lesson19.user;

import org.geekhub.lesson19.config.GeneralRepository;
import org.geekhub.lesson19.config.GeneralRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class UserRepository extends GeneralRepositoryImpl<User, Integer> implements GeneralRepository<User, Integer> {
    UserRepository() {
        super(User.class);
    }

    public Optional<User> findBy(String username) {
        return entityManager.createQuery("SELECT u FROM user u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }
}
