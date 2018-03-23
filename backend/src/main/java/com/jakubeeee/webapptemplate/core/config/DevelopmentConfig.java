package com.jakubeeee.webapptemplate.core.config;

import com.jakubeeee.webapptemplate.security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
@Profile("dev")
@PropertySource("classpath:application-dev.properties")
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner demo(UserService userService) {
        return (args) -> {
            userService.createUser("test1", "password1", "testmail1@mail.com");
            userService.createUser("test2", "password2", "testmail2@mail.com");
            userService.createUser("test3", "password3", "testmail3@mail.com");
        };
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        return loggingFilter;
    }

}
