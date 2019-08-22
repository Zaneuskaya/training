package com.senla.pdp;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.senla.pdp.controller")
@ImportMatrixServiceModuleConfiguration
public class MatrixControllerConfiguration {

}
