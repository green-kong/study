package com.example.productorderservice.product;

import org.springframework.util.Assert;

public record GetProductResponse(Long id, String name, int price, String discountPolicy) {
    public GetProductResponse {
        Assert.notNull(id, "아이디 null");
        Assert.hasText(name, "product 이름 없음");
        Assert.hasText(discountPolicy, "discountPolicy");
    }
}
