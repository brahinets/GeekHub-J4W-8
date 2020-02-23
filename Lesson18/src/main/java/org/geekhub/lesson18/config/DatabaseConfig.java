package org.geekhub.lesson18.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:database.properties")
public class DatabaseConfig {
    @Bean
    @Primary
    public DataSource dataSource1(@Value("${database.url}") String url,
                                  @Value("${database.username}") String username,
                                  @Value("${database.password}") String password) {
        final HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        return new HikariDataSource(config);
    }

    @Bean
    public DataSource dataSource2(@Value("${database.url}") String url,
                                  @Value("${database.username}") String username,
                                  @Value("${database.password}") String password) {
        final HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        return new HikariDataSource(config);
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
                .ignoreFutureMigrations(true)
                .ignoreMissingMigrations(true)
                .outOfOrder(true)
                .locations("classpath:db/migration")
                .dataSource(dataSource)
                .load();
    }

    @Bean
    public InitializingBean flywayMigrate(Flyway flyway) {
        return flyway::migrate;
    }
}
