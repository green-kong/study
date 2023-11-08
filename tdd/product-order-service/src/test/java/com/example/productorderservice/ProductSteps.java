package com.example.productorderservice;

import org.springframework.http.MediaType;

import com.example.productorderservice.product.DiscountPolicy;
import com.example.productorderservice.product.ProductAddRequest;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class ProductSteps {
    public static ExtractableResponse<Response> 상품등록요청(final ProductAddRequest request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/products")
                .then().log().all()
                .extract();
    }

    public static ProductAddRequest 상품등록요청_생성() {
        final String productName = "상품명";
        final int productPrice = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        return new ProductAddRequest(productName, productPrice, discountPolicy);
    }
}
