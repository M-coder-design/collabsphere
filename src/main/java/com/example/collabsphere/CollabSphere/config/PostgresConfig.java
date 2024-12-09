package com.example.collabsphere.CollabSphere.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.collabsphere.CollabSphere.repository.postgresql",
        entityManagerFactoryRef = "postgresEntityManagerFactory",
        transactionManagerRef = "postgresEntityManagerFactory"
)
public class PostgresConfig {

    @Bean(name = "postgresDataSource")
    @ConfigurationProperties("spring.datasource.postgresql")
    public DataSource postgresDataSource() {
        return new HikariDataSource();
    }

    @Bean(name = "postgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(postgresDataSource());
        factoryBean.setPackagesToScan("com.example.collabsphere.CollabSphere.model.postgresql"); // Scan PostgreSQL-specific entities
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update"); // Auto-create tables
        jpaProperties.put("hibernate.show_sql", "true"); // Show SQL queries
        jpaProperties.put("hibernate.format_sql", "true"); // Format SQL queries

        factoryBean.setJpaProperties(jpaProperties);
        return factoryBean;
    }

    @Bean(name = "postgresTransactionManager")
    public JpaTransactionManager postgresTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(postgresEntityManagerFactory().getObject());
        return transactionManager;
    }
}
