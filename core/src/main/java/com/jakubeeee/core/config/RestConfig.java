package com.jakubeeee.core.config;

import com.jakubeeee.core.interceptors.RequestLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;

@Configuration
public class RestConfig {

    @Autowired
    RequestLoggingInterceptor requestLoggingInterceptor;

    @Value("${enable.request.logging.interceptor}")
    boolean IS_REQUEST_LOGGER_INTERCEPTOR_ENABLED;

    @Bean
    public RestTemplate restTemplate() {
        var requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setOutputStreaming(false);
        var restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(requestFactory));
        var interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        if (IS_REQUEST_LOGGER_INTERCEPTOR_ENABLED) interceptors.add(requestLoggingInterceptor);
        restTemplate.setInterceptors(interceptors);
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

}
