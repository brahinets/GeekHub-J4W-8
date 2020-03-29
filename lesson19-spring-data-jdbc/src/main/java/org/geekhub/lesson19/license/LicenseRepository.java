package org.geekhub.lesson19.license;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface LicenseRepository extends CrudRepository<License, Integer> {
    @Override
    List<License> findAll();

    @Query("SELECT * FROM license WHERE user_id = :userId")
    List<License> findAllByUserId(@Param("userId") int userId);
}
