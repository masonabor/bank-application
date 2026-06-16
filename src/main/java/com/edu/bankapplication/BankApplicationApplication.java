package com.edu.bankapplication;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BankApplicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApplicationApplication.class, args);
    }

    @Bean
    ApplicationRunner mappings(@Qualifier("requestMappingHandlerMapping") RequestMappingHandlerMapping mapping) {
        return args -> {
            mapping.getHandlerMethods()
                    .forEach((k, v) -> System.out.println(k));
        };
    }
}
