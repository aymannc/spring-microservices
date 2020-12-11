package com.anc.customerservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication implements CommandLineRunner {
    final CustomerRepository customerRepository;
    final RepositoryRestConfiguration repositoryRestConfiguration;

    public CustomerServiceApplication(CustomerRepository customerRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
        this.customerRepository = customerRepository;
        this.repositoryRestConfiguration = repositoryRestConfiguration;
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Customer.class);
        customerRepository.save(new Customer(1L, "Ayman", "ayman@nait.com"));
        customerRepository.save(new Customer(2L, "Med", "med@med.com"));
        customerRepository.save(new Customer(3L, "Imane", "imane@imane.com"));
    }
}
