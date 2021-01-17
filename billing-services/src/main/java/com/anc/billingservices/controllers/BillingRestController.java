package com.anc.billingservices.controllers;

import com.anc.billingservices.entities.Bill;
import com.anc.billingservices.entities.ProductItem;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@RestController
public class BillingRestController {

    final String AUTH_TOKEN = "Authorization";
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
    public Bill getBill(HttpServletRequest request, @PathVariable(name = "id") Long id) throws NotFoundException {
        Optional<Bill> optionalBill = billRepository.findById(id);
        if (!optionalBill.isPresent()) {
            throw new NotFoundException("No user with this id");
        } else {
            Bill bill = optionalBill.get();
            bill.setCustomer(customerRestClient.getCustomerById(request.getHeader(AUTH_TOKEN), bill.getCustomerID()));
            bill.setProductItems(productItemRepository.findByBillId(id));
            bill.getProductItems().forEach(productItem -> {
                Product product = productsRestClient.getProductById(request.getHeader(AUTH_TOKEN),productItem.getProductID());
                productItem.setProduct(product);
            });
            return bill;
        }

    }

    @GetMapping(path = "/init")
    public void initData(HttpServletRequest request) throws NotFoundException {
        System.out.println(request.getHeader(AUTH_TOKEN));
        Bill bill = new Bill();
        bill.setBillingDate(new Date());
        Customer customer = customerRestClient.getCustomerById(request.getHeader(AUTH_TOKEN), 1L);
        bill.setCustomerID(customer.getId());
        billRepository.save(bill);

        productsRestClient.findAll(request.getHeader(AUTH_TOKEN)).getContent().forEach(p -> {
            productItemRepository.save(new ProductItem(null, (int) (Math.random() * 10 + 1), p.getId(), p.getPrice(),
                    p, bill));
        });

    }
}
