package com.giong.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.giong.config.security.SecurityConfig;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.giong" })
@Import({ SecurityConfig.class })
@PropertySource({ "classpath:application.properties" })
public class ApplicationConfig {
	
	private final String DRIVER_CLASS = "driverClass";
	private final String JDBC_URL = "jdbcUrl";
	private final String USER = "user";
	private final String PASSWORD = "password";
	private final String HIBERNATE_DIALECT = "hibernate.dialect";
	private final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	
	@Resource
	private Environment env;
	
	@Bean(name = "sessionFactory")
	public SessionFactory sessionFactory() throws PropertyVetoException {
		final LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(this.dataSource());
		builder.scanPackages("com.giong.model").addProperties(this.getHibernateProperties());
		
		return builder.buildSessionFactory();
	}
	
	@Bean(name = "dataSource")
	public ComboPooledDataSource dataSource() throws PropertyVetoException {
		
		final ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(this.env.getProperty(this.DRIVER_CLASS));
		dataSource.setJdbcUrl(this.env.getProperty(this.JDBC_URL));
		dataSource.setUser(this.env.getProperty(this.USER));
		dataSource.setPassword(this.env.getProperty(this.PASSWORD));
		return dataSource;
	}
	
	/**
	 * Create a transaction manager
	 * 
	 * @return
	 * @throws PropertyVetoException
	 */
	@Bean(name = "transactionManager")
	public HibernateTransactionManager transactionManager() throws PropertyVetoException {
		return new HibernateTransactionManager(this.sessionFactory());
	}
	
	private Properties getHibernateProperties() {
		final Properties prop = new Properties();
		prop.put(this.HIBERNATE_DIALECT, this.env.getProperty(this.HIBERNATE_DIALECT));
		prop.put(this.HIBERNATE_SHOW_SQL, this.env.getProperty(this.HIBERNATE_SHOW_SQL));
		return prop;
	}
}
