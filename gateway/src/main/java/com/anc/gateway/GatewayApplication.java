package com.anc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    //    @Bean
    RouteLocator routerLocator(RouteLocatorBuilder builder) {
        return builder.routes()
//                .route((r) -> r.path("/customers/**").uri("http://localhost:8081/customers"))
//                .route((r) -> r.path("/products/**").uri("http://localhost:8082/products"))
                .route((r) -> r.path("/customers/**").uri("lb://CUSTOMERS-SERVICE"))
                .route((r) -> r.path("/products/**").uri("lb://PRODUCTS-SERVICE"))
                .build();
    }

    @Bean
    DiscoveryClientRouteDefinitionLocator definitionLocator(
            ReactiveDiscoveryClient reactiveDiscoveryClient,
            DiscoveryLocatorProperties properties) {
        return new DiscoveryClientRouteDefinitionLocator(reactiveDiscoveryClient, properties);
    }

}
