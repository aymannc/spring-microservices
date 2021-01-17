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

}
