package com.example.concurrency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.concurrency.domain.Stock;

public interface LockRepository extends JpaRepository<Stock, Long> {

    @Query(value = "SELECT get_lock(:key,3000)", nativeQuery = true)
    void getLock(@Param("key") final String key);

    @Query(value = "SELECT release_lock(:key)", nativeQuery = true)
    void releaseLock(@Param("key") final String key);
}
