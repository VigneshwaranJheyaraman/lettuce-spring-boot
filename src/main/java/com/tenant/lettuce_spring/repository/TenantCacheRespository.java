package com.tenant.lettuce_spring.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class TenantCacheRespository implements CacheRepository{

    private StringRedisTemplate redisTemplate;
    private ValueOperations<String, String> valueOperations;

    @Autowired
    public TenantCacheRespository(StringRedisTemplate template, RedisStandaloneConfiguration redisStandaloneConfiguration) {
        this.redisTemplate = template;
        this.valueOperations = template.opsForValue();
    }

    @Override
    public void put(String key, String value) {
        this.valueOperations.set(key, value);
    }

    @Override
    public Optional<String> get(String key) {
        if (this.redisTemplate.hasKey(key).equals(Boolean.TRUE)) {
            String value = this.valueOperations.get(key);
            return Optional.ofNullable(value);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void remove(String key) {
        this.redisTemplate.delete(key);
    }
    
}
