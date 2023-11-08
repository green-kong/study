package com.example.productorderservice.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private int productPrice;
    private DiscountPolicy discountPolicy;

    public Product() {
    }

    Product(final String productName, final int productPrice, final DiscountPolicy discountPolicy) {
        this(null, productName, productPrice, discountPolicy);
    }

    public Product(final Long id, final String productName, final int productPrice,
                   final DiscountPolicy discountPolicy) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.discountPolicy = discountPolicy;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }
}
