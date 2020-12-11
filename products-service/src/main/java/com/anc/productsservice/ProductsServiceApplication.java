package com.anc.productsservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class ProductsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository,RepositoryRestConfiguration repositoryRestConfiguration) {
        return args -> {

            repositoryRestConfiguration.exposeIdsFor(Product.class);
            productRepository.save(new Product(1L, "S10", 9999.99, 2));
            productRepository.save(new Product(1L, "A7", 2999.99, 3));
        };
    }
}
