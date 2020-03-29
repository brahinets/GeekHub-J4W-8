package org.geekhub.lesson19.repository;

import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GeneralRepository<T extends Persistable<PK>, PK extends Serializable> {
    Optional<T> findBy(PK id);

    List<T> findAll();

    T save(T user);

    void delete(T user);
}
