package com.example.UserService.myConfig;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configs {

    @LoadBalanced
    @Bean
    public RestTemplate restClient() {
        return new RestTemplate();
    }
}
