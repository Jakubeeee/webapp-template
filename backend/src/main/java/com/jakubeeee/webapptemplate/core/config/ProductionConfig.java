package com.jakubeeee.webapptemplate.core.config;

import com.jakubeeee.webapptemplate.security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("prod")
@PropertySource("classpath:application-prod.properties")
public class ProductionConfig {

    @Bean
    public CommandLineRunner demo(UserService userService) {
        return (args) -> userService.createUser("admin", "password", "testmail@mail.com");
    }

}
