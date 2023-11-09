package com.example.gracefulshutdown.api;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class ApiTest {

    @BeforeEach
    void setUp() {
        RestAssured.port = 8080;
    }

    @Test
    @DisplayName("그레이스풀 셧다운 테스트")
    void gracefulShutDownTest() throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(8);
        final CountDownLatch latch = new CountDownLatch(8);
        for (int i = 0; i < 8; i++) {
            final int finalI = i;
            executorService.submit(() -> {
                try {
                RestAssured.given().log().all()
                        .when()
                        .get("/graceful/" + finalI)
                        .then().log().all()
                        .extract();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
    }
}
