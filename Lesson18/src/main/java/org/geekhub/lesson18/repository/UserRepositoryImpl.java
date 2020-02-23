package org.geekhub.lesson18.repository;

import org.geekhub.lesson18.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findBy(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT firstName, lastName FROM users WHERE id = :userId",
                new MapSqlParameterSource("userId", id),
                (rs, rowNum) -> {
                    User actor1 = new User();
                    actor1.setFirstName(rs.getString("firstName"));
                    actor1.setLastName(rs.getString("lastName"));
                    return actor1;
                });
    }

    public User findByBPRM(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT firstName, lastName FROM users WHERE id = :userId",
                new MapSqlParameterSource("userId", id),
                new BeanPropertyRowMapper<>(User.class)
        );
    }
}
