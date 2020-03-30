package org.geekhub.lesson19.config;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
public class DatabaseConfig {
    @Bean
    @Primary
    public DataSource dataSource() {
        final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.postgresql.Driver.class);
        dataSource.setUrl("jdbc:postgresql://localhost:5432/test_db");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");
        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(@Value("${db.hibernate.fetch.size}") int fetchSize,
                                                     @Value("${db.hibernate.batch.size}") int batchSize,
                                                     DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setJpaProperties(hibernateProperties(fetchSize, batchSize));
        factory.setPackagesToScan("org.geekhub.lesson19");
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory, DataSource dataSource) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        txManager.setDataSource(dataSource);
        return txManager;
    }

    private static Properties hibernateProperties(int fetchSize, int batchSize) {
        final Properties properties = new Properties();
        properties.put(AvailableSettings.ORDER_INSERTS, true);
        properties.put(AvailableSettings.ORDER_UPDATES, true);
        properties.put(AvailableSettings.AUTOCOMMIT, false);
        properties.put(AvailableSettings.ENABLE_LAZY_LOAD_NO_TRANS, true);

        properties.put(AvailableSettings.DIALECT, org.hibernate.dialect.PostgreSQL10Dialect.class.getName());
        properties.put(AvailableSettings.SHOW_SQL, true);
        properties.put(AvailableSettings.FORMAT_SQL, true);
        properties.put(AvailableSettings.USE_SQL_COMMENTS, true);
        properties.put(AvailableSettings.STATEMENT_FETCH_SIZE, fetchSize);
        properties.put(AvailableSettings.STATEMENT_BATCH_SIZE, batchSize);

        properties.put(AvailableSettings.GENERATE_STATISTICS, true);
        properties.put(AvailableSettings.USE_STRUCTURED_CACHE, true);
        return properties;
    }
}
