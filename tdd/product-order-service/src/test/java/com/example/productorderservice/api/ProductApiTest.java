package com.example.productorderservice.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.example.productorderservice.ProductSteps;

class ProductApiTest extends ApiTest {

    @Test
    @DisplayName("상품등록")
    void applyProduct() {
        //given
        final var request = ProductSteps.상품등록요청_생성();

        //when
        final var response = ProductSteps.상품등록요청(request);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

}
