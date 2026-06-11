package com.edu.bankapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BankAplicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAplicationApplication.class, args);
    }

}
