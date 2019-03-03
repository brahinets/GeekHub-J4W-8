package org.geekhub.lesson19.repository;

import org.geekhub.lesson19.db.persistence.Persistable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GeneralRepository<T extends Persistable<PK>, PK extends Serializable> {
    Optional<T> findBy(PK id);

    List<T> findAll();

    T save(T user);

    void delete(T user);
}
