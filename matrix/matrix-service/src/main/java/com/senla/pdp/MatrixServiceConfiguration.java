package com.senla.pdp;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.senla.pdp.service")
@PropertySource("classpath:spring.mail.properties")
@ImportMatrixRepositoryModuleConfiguration
public class MatrixServiceConfiguration {

}
