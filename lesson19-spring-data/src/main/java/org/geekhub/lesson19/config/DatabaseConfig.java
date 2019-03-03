package org.geekhub.lesson19.config;

import org.hibernate.ConnectionReleaseMode;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUrl("jdbc:h2:mem:lesson19;DB_CLOSE_DELAY=-1");
        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(Environment environment, DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setPackagesToScan("org.geekhub.lesson19.db.persistence");
        factory.setJpaProperties(hibernateProperties(environment, true));
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(Environment environment, DataSource dataSource) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory(environment, dataSource));
        txManager.setDataSource(dataSource);
        return txManager;
    }

    private static Properties hibernateProperties(Environment environment, boolean useStatistics) {
        final Properties properties = new Properties();
        properties.put(AvailableSettings.ORDER_INSERTS, true);
        properties.put(AvailableSettings.ORDER_UPDATES, true);
        properties.put(AvailableSettings.HBM2DDL_AUTO, "create-drop");//do not do this on real projects
        properties.put(AvailableSettings.AUTOCOMMIT, false);
        properties.put(AvailableSettings.ENABLE_LAZY_LOAD_NO_TRANS, true);

        properties.put(AvailableSettings.DIALECT, org.hibernate.dialect.H2Dialect.class.getName());
        properties.put(AvailableSettings.SHOW_SQL, true);
        properties.put(AvailableSettings.FORMAT_SQL, true);
        properties.put(AvailableSettings.USE_SQL_COMMENTS, true);
        properties.put(AvailableSettings.STATEMENT_BATCH_SIZE, environment.getProperty("db.hibernate.batch_size", Integer.class));
        properties.put(AvailableSettings.STATEMENT_FETCH_SIZE, environment.getProperty("db.hibernate.fetch_size", Integer.class));

        properties.put(AvailableSettings.GENERATE_STATISTICS, useStatistics);
        properties.put(AvailableSettings.USE_STRUCTURED_CACHE, useStatistics);
        properties.put(AvailableSettings.CONNECTION_HANDLING, ConnectionReleaseMode.ON_CLOSE);
        return properties;
    }
}
