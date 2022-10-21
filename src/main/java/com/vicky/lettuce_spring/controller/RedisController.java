package com.vicky.lettuce_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vicky.lettuce_spring.data.SubscriptionResponse;
import com.vicky.lettuce_spring.repository.CacheRepository;

@RestController
@RequestMapping("/api/services")
public class RedisController {
    private final CacheRepository cacheRepository;
    
    @Autowired
    public RedisController(CacheRepository cacheRepository) {
        this.cacheRepository= cacheRepository;
    }

    private ResponseEntity<SubscriptionResponse<String>> errorMiddleware(Exception e){
        return (ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new SubscriptionResponse<String>(
                    HttpStatus.NOT_FOUND.value(),
                    null,
                    e.getMessage())));
    }

    @GetMapping("/{tenantId}")
    public ResponseEntity<SubscriptionResponse<String>> getSubscriptions(@PathVariable String tenantId) {
        try {
            return (ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new SubscriptionResponse<String>(200, this.cacheRepository.get(tenantId).get(), null)));
        } catch (Exception e) {
            return this.errorMiddleware(e);
        }
    }
}