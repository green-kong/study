package com.example.concurrency.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.concurrency.domain.Stock;
import com.example.concurrency.repository.StockRepository;

@Service
public class PessimisticLockStockService {

    private StockRepository stockRepository;

    public PessimisticLockStockService(final StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public long decrease(final Long id, long quantity) {
        final Stock stock = stockRepository.findByIdPessimisticLock(id)
                .orElseThrow(() -> new IllegalArgumentException("asdfa"));

        return stock.decrease(quantity);
    }
}
