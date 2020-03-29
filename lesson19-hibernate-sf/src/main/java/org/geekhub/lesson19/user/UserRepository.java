package org.geekhub.lesson19.user;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
class UserRepository {
    private final SessionFactory sessionFactory;

    UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<User> findBy(Integer id) {
        if (null == id) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(sessionFactory.getCurrentSession().get(User.class, id));
        }
    }

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

    public User save(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }
}
