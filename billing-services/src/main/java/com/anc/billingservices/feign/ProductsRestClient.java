package com.anc.billingservices.feign;

import com.anc.billingservices.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

//@FeignClient(name = "PRODUCTS-SERVICE", url = "http://localhost:8082")
@FeignClient(name = "PRODUCTS-SERVICE")
public interface ProductsRestClient {
//    @GetMapping(path = "/products")
//    PagedModel<Product> pageProducts(@QueryParam(value = "page") int page,
//                                     @QueryParam(value = "size") int size);

    String AUTH_TOKEN = "Authorization";

    @GetMapping(path = "/products/{id}")
    Product getProductById(@RequestHeader(value = AUTH_TOKEN) String bearerToken, @PathVariable Long id);

    @GetMapping("/products")
    PagedModel<Product> findAll(@RequestHeader(value = AUTH_TOKEN) String bearerToken);

}
