package com.example.productorderservice.product;

import org.springframework.util.Assert;

public record ProductAddRequest(String productName, int productPrice, DiscountPolicy discountPolicy) {
    public ProductAddRequest {
        Assert.hasText(productName, "상품명을 입력해주세요.");
        Assert.isTrue(productPrice > 0, "상품가격은 0보다 커야합니다.");
        Assert.notNull(discountPolicy, "할인정책은 필수 입니다.");
    }
}
