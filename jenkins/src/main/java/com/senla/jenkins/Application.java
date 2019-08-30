package com.senla.jenkins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.out.println("!!!!!!!!!!!!!!!!!Hello from new version");
        SpringApplication.run(Application.class, args);
    }
}
