package com.anc.billingservices.feign;

import com.anc.billingservices.models.Customer;
import com.anc.billingservices.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMERS-SERVICE")
public interface CustomerRestClient {
    @GetMapping(path = "/customers/{id}")
    Customer getCustomerById(@PathVariable(name = "id") Long id);
    @GetMapping("/customers")
    PagedModel<Customer> findAll();
}
