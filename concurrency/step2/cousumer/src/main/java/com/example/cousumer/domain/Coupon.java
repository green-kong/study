package com.example.cousumer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    protected Coupon() {
    }

    public Coupon(final Long id, final Long userId) {
        this.id = id;
        this.userId = userId;
    }

    public Coupon(final Long userId) {
        this(null, userId);
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }
}
