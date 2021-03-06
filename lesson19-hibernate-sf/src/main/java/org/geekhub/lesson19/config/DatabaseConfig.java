package org.geekhub.lesson19.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
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
    public LocalSessionFactoryBean sessionFactory(@Value("${db.hibernate.fetch.size}") int fetchSize,
                                                  @Value("${db.hibernate.batch.size}") int batchSize,
                                                  DataSource dataSource) throws IOException {
        final LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setPackagesToScan("org.geekhub.lesson19");
        factory.setHibernateProperties(hibernateProperties(fetchSize, batchSize));
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    private static Properties hibernateProperties(int fetchSize, int batchSize) {
        final Properties properties = new Properties();
        properties.put(AvailableSettings.ORDER_INSERTS, true);
        properties.put(AvailableSettings.ORDER_UPDATES, true);
        properties.put(AvailableSettings.HBM2DDL_AUTO, "create-drop"); //do not do this on real projects
        properties.put(AvailableSettings.AUTOCOMMIT, false);
        properties.put(AvailableSettings.ENABLE_LAZY_LOAD_NO_TRANS, true);

        properties.put(AvailableSettings.DIALECT, org.hibernate.dialect.H2Dialect.class.getName());
        properties.put(AvailableSettings.FORMAT_SQL, true);
        properties.put(AvailableSettings.SHOW_SQL, true);
        properties.put(AvailableSettings.USE_SQL_COMMENTS, true);
        properties.put(AvailableSettings.STATEMENT_FETCH_SIZE, fetchSize);
        properties.put(AvailableSettings.STATEMENT_BATCH_SIZE, batchSize);

        properties.put(AvailableSettings.GENERATE_STATISTICS, true);
        properties.put(AvailableSettings.USE_STRUCTURED_CACHE, true);
        return properties;
    }
}
