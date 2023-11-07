package com.example.cousumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cousumer.domain.Coupon;
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
