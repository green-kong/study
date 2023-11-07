package com.example.api.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AppliedRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public AppliedRepository(final RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Long apply(final Long userId) {
        return redisTemplate
                .opsForSet()
                .add("apply", userId.toString());
    }
}
