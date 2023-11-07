package com.example.productorderservice.product;

class Product {

    private Long id;
    private final String productName;
    private final int productPrice;
    private final DiscountPolicy discountPolicy;

    Product(final String productName, final int productPrice, final DiscountPolicy discountPolicy) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.discountPolicy = discountPolicy;
    }

    public void assignId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
