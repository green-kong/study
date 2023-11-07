package com.example.concurrency.facade;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.concurrency.repository.LockRepository;
import com.example.concurrency.service.StockService;

@Component
public class NameLockStockFacade {

    private final LockRepository lockRepository;
    private final StockService stockService;

    public NameLockStockFacade(final LockRepository lockRepository, final StockService stockService) {
        this.lockRepository = lockRepository;
        this.stockService = stockService;
    }

    @Transactional
    public void decrease(final Long id, final long quantity) {
        try {
            lockRepository.getLock(id.toString());
            stockService.decrease(id, quantity);
        } finally {
            lockRepository.releaseLock(id.toString());
        }
    }
}
