package com.example.concurrency.facade;

import org.springframework.stereotype.Component;

import com.example.concurrency.service.OptimisticLockStockService;

@Component
public class OptimisticLockStockFacade {

    private final OptimisticLockStockService service;

    public OptimisticLockStockFacade(final OptimisticLockStockService service) {
        this.service = service;
    }

    public void decrease(final Long id, final long quantity) throws InterruptedException {
        while (true) {
            try {
                service.decrease(id, quantity);
                break;
            } catch (RuntimeException exception) {
                Thread.sleep(50);
            }
        }
    }
}
