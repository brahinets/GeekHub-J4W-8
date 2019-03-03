package org.geekhub.lesson19.repository;

import org.geekhub.lesson19.db.persistence.Persistable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class GeneralRepositoryImpl<T extends Persistable<PK>, PK extends Serializable> implements GeneralRepository<T, PK> {
    protected final Class<T> domainClass;
    @PersistenceContext protected EntityManager entityManager;

    protected GeneralRepositoryImpl(Class<T> domainClass) {
        this.domainClass = domainClass;
    }

    @Override
    public Optional<T> findBy(PK id) {
        if (null == id) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(entityManager.find(domainClass, id));
        }
    }

    @Override
    public List<T> findAll() {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<T> query = builder.createQuery(domainClass);
        final Root<T> root = query.from(domainClass);
        return entityManager.createQuery(query.select(root)).getResultList();
    }

    @Override
    public T save(T user) {
        if (null == user.getId()) {
            entityManager.persist(user);
            return user;
        } else {
            return entityManager.merge(user);
        }
    }

    @Override
    public void delete(T user) {
        entityManager.remove(entityManager.merge(user));
    }
}
