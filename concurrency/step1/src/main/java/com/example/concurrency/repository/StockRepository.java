package com.example.concurrency.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.concurrency.domain.Stock;

import jakarta.persistence.LockModeType;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Stock s WHERE s.id = :id")
    Optional<Stock> findByIdPessimisticLock(@Param("id") final Long id);


    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT s FROM Stock s WHERE s.id = :id")
    Optional<Stock> findByIdWithOptimisticLock(@Param("id") final Long id);
}
