package com.example.productorderservice.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.productorderservice.ProductSteps;

@SpringBootTest
class productServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("상품등록")
    void applyProduct() {
        //given
        final ProductAddRequest request = ProductSteps.상품등록요청_생성();

        //when
        productService.addProduct(request);

        //then
    }

    @Test
    @DisplayName("상품 조회")
    void getProduct() {
        //given
        productService.addProduct(ProductSteps.상품등록요청_생성());
        Long productId = 1L;

        //when
        final GetProductResponse response = productService.findProduct(productId);

        //then
        assertSoftly(softAssertions -> {
            assertThat(response).isNotNull();
            assertThat(response.id()).isEqualTo(productId);
        });
    }
}
