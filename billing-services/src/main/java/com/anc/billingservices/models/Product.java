package com.anc.billingservices.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Product {
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
}
