package com.anc.billingservices;

import com.anc.billingservices.entities.Bill;
import com.anc.billingservices.entities.ProductItem;
import com.anc.billingservices.feign.CustomerRestClient;
import com.anc.billingservices.feign.ProductsRestClient;
import com.anc.billingservices.models.Customer;
import com.anc.billingservices.repositories.BillRepository;
import com.anc.billingservices.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class BillingServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServicesApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository,
                            ProductsRestClient productsRestClient, CustomerRestClient customerRestClient) {
        return args -> {
            Bill bill = new Bill();
            bill.setBillingDate(new Date());
            Customer customer = customerRestClient.getCustomerById(1L);
            bill.setCustomerID(customer.getId());
            bill.setCustomer(customer);
            billRepository.save(bill);

            productsRestClient.findAll().getContent().forEach(p -> {
                productItemRepository.save(new ProductItem(null, (int) (Math.random() * 10 + 1), p.getId(), p.getPrice(),
                        p, bill));
            });
            billRepository.findAll().forEach(System.out::println);
        };
    }

}
