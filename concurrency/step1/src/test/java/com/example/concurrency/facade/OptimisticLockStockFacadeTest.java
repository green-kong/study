package com.example.concurrency.facade;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.concurrency.domain.Stock;
import com.example.concurrency.repository.StockRepository;

@SpringBootTest
class OptimisticLockStockFacadeTest {


    @Autowired
    private OptimisticLockStockFacade facade;

    @Autowired
    private StockRepository stockRepository;

    private Stock stock;

    @BeforeEach
    void setUp() {
        stock = stockRepository.save(new Stock(1L, 100L));
    }

    @AfterEach
    void tearDown() {
        stockRepository.deleteAll();
    }

    @Test
    @DisplayName("qwer")
    void decreaseWithPessimisticLock() throws InterruptedException {
        //when
        final ExecutorService executorService = Executors.newFixedThreadPool(32);
        final CountDownLatch latch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    facade.decrease(stock.getId(), 1L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        final Stock stock1 = stockRepository.findById(stock.getId()).get();

        //then
        assertThat(stock1.getQuantity()).isEqualTo(0L);
    }

}
