package com.example.productorderservice.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class productServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("상품등록")
    void applyProduct() {
        //given
        final ProductAddRequest request = 상품등록요청_생성();

        //when
        productService.addProduct(request);

        //then
    }

    private static ProductAddRequest 상품등록요청_생성() {
        final String productName = "상품명";
        final int productPrice = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        return new ProductAddRequest(productName, productPrice, discountPolicy);
    }

}
