package com.example.concurrency.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private long quantity;

    @Version
    private Long version;

    protected Stock() {
    }

    public Stock(final Long productId, final long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public long decrease(final long quantity) {
        if(this.quantity< quantity) {
            throw new IllegalArgumentException("불가");
        }

        this.quantity = this.quantity - quantity;
        return this.quantity;
    }
}
