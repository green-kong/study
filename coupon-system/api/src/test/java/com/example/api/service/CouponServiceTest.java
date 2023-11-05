package com.example.api.service;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.api.repository.CouponRepository;

@SpringBootTest
class CouponServiceTest {

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponRepository couponRepository;

    @Test
    public void couponApplyTest() {
        couponService.apply(1L);

        final long count = couponRepository.count();

        assertThat(count).isEqualTo(1);
    }


    @Test
    @DisplayName("asdf")
    void multiple_apply_test() throws InterruptedException {
        //given
        final ExecutorService executorService = Executors.newFixedThreadPool(32);
        final CountDownLatch latch = new CountDownLatch(1000);

        //when
        for (int i = 0; i < 1000; i++) {
            long userId = i;
            executorService.submit(() -> {
                try {
                    couponService.apply(userId);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        final long count = couponRepository.count();

        //then
        assertThat(count).isEqualTo(100);
    }
}
