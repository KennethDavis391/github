package com.example.APIGatewayApplication.gateway;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r->r.path("/users/**")
                        .uri("lb://USER-SERVICE"))
                .route(r->r.path("/lists/**")
                        .uri("lb://USER-SERVICE"))
                .route(r->r.path("/comments/**")
                        .uri("lb://POST-SERVICE"))
                .route(r->r.path("/posts/**")
                        .uri("lb://POST-SERVICE"))
                .build();

    }
}
