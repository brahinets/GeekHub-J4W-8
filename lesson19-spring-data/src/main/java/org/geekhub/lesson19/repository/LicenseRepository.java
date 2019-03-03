package org.geekhub.lesson19.repository;

import org.geekhub.lesson19.db.persistence.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends JpaRepository<License, Integer> {
}
