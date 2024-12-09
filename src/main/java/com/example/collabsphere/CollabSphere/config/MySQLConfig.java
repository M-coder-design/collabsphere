package com.example.collabsphere.CollabSphere.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.collabsphere.CollabSphere.repository.mysql",
        entityManagerFactoryRef = "mySqlEntityManagerFactory",
        transactionManagerRef = "mySqlTransactionManager"
)
public class MySQLConfig {

    @Bean(name = "mySqlDataSource")
    @ConfigurationProperties("spring.datasource.mysql")
    public DataSource mySqlDataSource() {
        return new HikariDataSource();
    }

    @Bean(name = "mySqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(mySqlDataSource());
        factoryBean.setPackagesToScan("com.example.collabsphere.CollabSphere.model.mysql"); // Scan MySQL-specific entities
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update"); // Auto-create tables
        jpaProperties.put("hibernate.show_sql", "true"); // Show SQL queries
        jpaProperties.put("hibernate.format_sql", "true"); // Format SQL queries

        factoryBean.setJpaProperties(jpaProperties);
        return factoryBean;
    }

    @Bean(name = "mySqlTransactionManager")
    public JpaTransactionManager mySqlTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mySqlEntityManagerFactory().getObject());
        return transactionManager;
    }
}
