package com.anc.billingservices.models;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private String image;
    private Double price;
    private Integer quantity;
}
