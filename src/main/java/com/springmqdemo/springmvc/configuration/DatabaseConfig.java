package com.springmqdemo.springmvc.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
public class DatabaseConfig {

	@Bean
	public DataSource dataSource() throws Exception {
		Properties properties = new Properties();
		properties.setProperty("driverClassName", "org.h2.Driver");
		properties.setProperty("url", "jdbc:h2:mem:testdb;DB_CLOSE_ON_EXIT=FALSE");
		properties.setProperty("username", "sa");
		properties.setProperty("password", "");
		return BasicDataSourceFactory.createDataSource(properties);
	}
	
	@Bean
    public LocalSessionFactoryBean sessionFactory() throws Exception {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.springmqdemo.springmvc.model");
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProperties.put("hibernate.show_sql", "true");
        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }
	
	@Bean
    public HibernateTransactionManager transactionManager() throws Exception {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }
}
