package com.example.productorderservice.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.example.productorderservice.product.DiscountPolicy;
import com.example.productorderservice.product.ProductAddRequest;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

class ProductApiTest extends ApiTest {

    @Test
    @DisplayName("상품등록")
    void applyProduct() {
        //given
        final var request = 상품등록요청_생성();

        //when
        final var response = 상품등록요청(request);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private static ExtractableResponse<Response> 상품등록요청(final ProductAddRequest request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/products")
                .then().log().all()
                .extract();
    }

    private static ProductAddRequest 상품등록요청_생성() {
        final String productName = "상품명";
        final int productPrice = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        return new ProductAddRequest(productName, productPrice, discountPolicy);
    }

}
