package com.senla.pdp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:datasource.properties")
@ComponentScan(basePackages = "com.senla.pdp.repository")
public class MatrixRepositoryConfiguration {

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.jpa.showSql}")
    private String showSql;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;

    @Value("${spring.jpa.hibernate.ddlAuto}")
    private String ddlAuto;

    @Value("${spring.jpa.properties.hibernate.formatSql}")
    private String formatSql;

    @Value("${hibernate.jdbc.lob.non_contextual_creation}")
    private String contextualCreation;

    @Bean
    public DataSource dataSource() {
	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	dataSource.setDriverClassName(driverClassName);
	dataSource.setUsername(username);
	dataSource.setPassword(password);
	dataSource.setUrl(url);
	return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	entityManagerFactoryBean.setDataSource(dataSource());
	entityManagerFactoryBean.setPackagesToScan("com.senla.pdp.model");
	entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	entityManagerFactoryBean.setJpaProperties(jpaProperties());
	return entityManagerFactoryBean;
    }

    private Properties jpaProperties() {
	Properties jpaProperties = new Properties();
	jpaProperties.put("hibernate.dialect", dialect);
	jpaProperties.put("hibernate.hbm2ddl.auto", ddlAuto);
	jpaProperties.put("hibernate.show_sql", showSql);
	jpaProperties.put("hibernate.format_sql", formatSql);
	jpaProperties.put("hibernate.jdbc.lob.non_contextual_creation", contextualCreation);
	return jpaProperties;
    }

}
