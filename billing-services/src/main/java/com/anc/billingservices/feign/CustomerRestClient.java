package com.anc.billingservices.feign;

import com.anc.billingservices.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "CustomerRestClient", url = "http://localhost:8081")
//@FeignClient(name = "CUSTOMERS-SERVICE")
public interface CustomerRestClient {
    String AUTH_TOKEN = "Authorization";

    @GetMapping(path = "/customers/{id}")
    Customer getCustomerById(@RequestHeader(value = AUTH_TOKEN) String bearerToken, @PathVariable(name = "id") Long id);

    @GetMapping("/customers")
    PagedModel<Customer> findAll(@RequestHeader(value = AUTH_TOKEN) String bearerToken);
}
