package com.anc.billingservices.feign;

import com.anc.billingservices.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.ws.rs.QueryParam;

@FeignClient(name = "PRODUCTS-SERVICE")
public interface ProductsRestClient {
//    @GetMapping(path = "/products")
//    PagedModel<Product> pageProducts(@QueryParam(value = "page") int page,
//                                     @QueryParam(value = "size") int size);

    @GetMapping(path = "/products/{id}")
    Product getProductById(@PathVariable Long id);

    @GetMapping("/products")
    PagedModel<Product> findAll();

}
