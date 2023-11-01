package com.example.concurrency.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.concurrency.domain.Stock;
import com.example.concurrency.repository.StockRepository;

@Service
@Transactional(readOnly = true)
public class StockService {

    private final StockRepository stockRepository;

    public StockService(final StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public long decrease(Long id, Long quantity) {

        final Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("asdf"));

        return stock.decrease(quantity);
    }
}
