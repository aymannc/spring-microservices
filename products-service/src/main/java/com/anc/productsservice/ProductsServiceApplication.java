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
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
        return args -> {

            repositoryRestConfiguration.exposeIdsFor(Product.class);
            productRepository.save(new Product(1L, "S10",
                    "https://fdn2.gsmarena.com/vv/pics/samsung/samsung-galaxy-s10-1.jpg", 9999.99, 2));
            productRepository.save(new Product(2L, "A7",
                    "https://fdn2.gsmarena.com/vv/pics/samsung/samsung-galaxy-a7-sm-a750f-1.jpg", 2999.99, 3));
            productRepository.save(new Product(3L, "IPhone 7",
                    "https://fdn2.gsmarena.com/vv/pics/apple/apple-iphone-7-1.jpg", 1999.99, 3));
        };
    }
}
