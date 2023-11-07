package com.example.cousumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.cousumer.domain.Coupon;
import com.example.cousumer.repository.CouponRepository;

@Component
public class CouponCreateConsumer {

    private final CouponRepository couponRepository;

    public CouponCreateConsumer(final CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @KafkaListener(topics = "coupon_create", groupId = "group_1")
    public void listener(final Long userId) {
        System.out.println(userId);
        couponRepository.save(new Coupon(userId));
    }
}
