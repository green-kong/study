package com.example.api.service;

import org.springframework.stereotype.Service;

import com.example.api.domain.Coupon;
import com.example.api.producer.CouponCreateProducer;
import com.example.api.repository.CouponCountRepository;
import com.example.api.repository.CouponRepository;

@Service
public class CouponService {

    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer producer;

    public CouponService(
            final CouponRepository couponRepository,
            final CouponCountRepository couponCountRepository,
            final CouponCreateProducer producer) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.producer = producer;
    }



    public void apply(final Long userId) {
        final long count = couponCountRepository.increment();

        if (count > 100) {
            throw new IllegalArgumentException("안됨");
        }

        producer.createCoupon(userId);
    }
}
