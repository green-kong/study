package com.example.api.service;

import org.springframework.stereotype.Service;

import com.example.api.domain.Coupon;
import com.example.api.repository.CouponCountRepository;
import com.example.api.repository.CouponRepository;

@Service
public class CouponService {

    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;

    public CouponService(
            final CouponRepository couponRepository,
            final CouponCountRepository couponCountRepository
    ) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
    }

    public void apply(final Long userId) {
        final long count = couponCountRepository.increment();

        if (count > 100) {
            throw new IllegalArgumentException("안됨");
        }

        couponRepository.save(new Coupon(userId));
    }
}
