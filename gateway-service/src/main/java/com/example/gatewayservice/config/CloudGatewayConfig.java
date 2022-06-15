package com.example.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudGatewayConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("my-app", r -> r.path("/users/**")
                        .uri("http://localhost:8100"))
                .route("my-app", r -> r.path("/assignments/**")
                        .uri("http://localhost:8400"))
                .build();

    }
}
