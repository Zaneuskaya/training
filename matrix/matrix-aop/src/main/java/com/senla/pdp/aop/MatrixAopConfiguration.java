package com.senla.pdp.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@ComponentScan(basePackages = "com.senla.pdp.aop")
@PropertySource("classpath:activemq.properties")
@EnableAspectJAutoProxy
@EnableJms
public class MatrixAopConfiguration {

}
