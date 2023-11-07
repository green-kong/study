package com.example.api.service;

import org.springframework.stereotype.Service;

import com.example.api.producer.CouponCreateProducer;
import com.example.api.repository.AppliedRepository;
import com.example.api.repository.CouponCountRepository;

@Service
public class CouponService {

    private final AppliedRepository appliedRepository;
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer producer;

    public CouponService(
            final AppliedRepository appliedRepository,
            final CouponCountRepository couponCountRepository,
            final CouponCreateProducer producer
    ) {
        this.appliedRepository = appliedRepository;
        this.couponCountRepository = couponCountRepository;
        this.producer = producer;
    }



    public void apply(final Long userId) {
        final Long apply = appliedRepository.apply(userId);
        if (!apply.equals(1L)) {
            return;
        }

        final long count = couponCountRepository.increment();

        if (count > 100) {
            throw new IllegalArgumentException("안됨");
        }

        producer.createCoupon(userId);
    }
}
