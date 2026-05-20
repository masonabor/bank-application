package com.edu.bankaplication.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password4j.ScryptPassword4jPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class SecurityConfig {

    SecurityFilterChain securityFilterChain(HttpSecurity http) {

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new ScryptPassword4jPasswordEncoder();
    }
}
