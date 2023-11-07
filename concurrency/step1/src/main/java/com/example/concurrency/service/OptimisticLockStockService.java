package com.example.concurrency.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.concurrency.domain.Stock;
import com.example.concurrency.repository.StockRepository;

@Service
public class OptimisticLockStockService {

    private final StockRepository stockRepository;

    public OptimisticLockStockService(final StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public long decrease(final Long id, final long quantity) {
        final Stock stock = stockRepository.findByIdWithOptimisticLock(id)
                .orElseThrow(() -> new IllegalArgumentException("123"));

        return stock.decrease(quantity);
    }
}
