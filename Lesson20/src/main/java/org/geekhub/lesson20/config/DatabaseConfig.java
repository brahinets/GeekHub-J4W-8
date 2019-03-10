package org.geekhub.lesson20.config;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DatabaseConfig {
    @Bean
    @Primary
    public DataSource dataSource() {
        final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUrl("jdbc:h2:mem:lesson20;DB_CLOSE_DELAY=-1");
        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setPackagesToScan("org.geekhub.lesson20.db.persistence");
        factory.setJpaProperties(hibernateProperties());
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory(dataSource));
        txManager.setDataSource(dataSource);
        return txManager;
    }

    private static Properties hibernateProperties() {
        final Properties properties = new Properties();
        properties.put(AvailableSettings.ORDER_INSERTS, true);
        properties.put(AvailableSettings.ORDER_UPDATES, true);
        properties.put(AvailableSettings.AUTOCOMMIT, false);
        properties.put(AvailableSettings.ENABLE_LAZY_LOAD_NO_TRANS, false);

        properties.put(AvailableSettings.DIALECT, org.hibernate.dialect.H2Dialect.class.getName());
        properties.put(AvailableSettings.SHOW_SQL, true);
        properties.put(AvailableSettings.FORMAT_SQL, true);
        properties.put(AvailableSettings.USE_SQL_COMMENTS, false);

        properties.put(AvailableSettings.GENERATE_STATISTICS, true);
        properties.put(AvailableSettings.USE_STRUCTURED_CACHE, true);
        return properties;
    }
}
