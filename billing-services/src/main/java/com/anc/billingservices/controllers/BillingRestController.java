package com.anc.billingservices.controllers;

import com.anc.billingservices.entities.Bill;
import com.anc.billingservices.feign.CustomerRestClient;
import com.anc.billingservices.feign.ProductsRestClient;
import com.anc.billingservices.models.Customer;
import com.anc.billingservices.models.Product;
import com.anc.billingservices.repositories.BillRepository;
import com.anc.billingservices.repositories.ProductItemRepository;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BillingRestController {
    BillRepository billRepository;
    ProductItemRepository productItemRepository;
    CustomerRestClient customerRestClient;
    ProductsRestClient productsRestClient;

    public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository,
                                 CustomerRestClient customerRestClient, ProductsRestClient productsRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productsRestClient = productsRestClient;
    }

    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id) throws NotFoundException {
        Optional<Bill> optionalBill = billRepository.findById(id);
        if (!optionalBill.isPresent()) {
            throw new NotFoundException("No user with this id");
        } else {
            Bill bill = optionalBill.get();
            Customer customer = customerRestClient.getCustomerById(bill.getCustomerID());
            bill.setCustomer(customer);
            bill.getProductItems().forEach(productItem -> {
                Product product = productsRestClient.getProductById(productItem.getProductID());
                productItem.setProduct(product);
            });
            return bill;
        }

    }
}
