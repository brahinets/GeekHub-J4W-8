package org.geekhub.lesson19.repository;

import org.geekhub.lesson19.db.persistence.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<User> findBy(Integer id) {
        if (null == id) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(sessionFactory.getCurrentSession().get(User.class, id));
        }
    }

    @Override
    public Optional<User> findBy(String username) {
        final CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        final CriteriaQuery<User> query = builder.createQuery(User.class);
        final Root<User> root = query.from(User.class);
        query.select(root);
        query.where(builder.equal(root.get("username"), username));
        return Optional.of(sessionFactory
                .getCurrentSession()
                .createQuery(query)
                .getSingleResult());
    }

    @Override
    public List<User> findAll() {
        final CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        final CriteriaQuery<User> query = builder.createQuery(User.class);
        final Root<User> root = query.from(User.class);
        query.select(root);
        return sessionFactory
                .getCurrentSession()
                .createQuery(query)
                .getResultList();
    }

    @Override
    public User save(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }
}
