package com.senla.pdp.application;

import com.senla.pdp.ImportMatrixControllerModuleConfiguration;
import com.senla.pdp.aop.ImportMatrixAopConfiguration;
import com.senla.pdp.security.ImportSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ImportSecurityConfiguration
@ImportMatrixAopConfiguration
@ImportMatrixControllerModuleConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

}
