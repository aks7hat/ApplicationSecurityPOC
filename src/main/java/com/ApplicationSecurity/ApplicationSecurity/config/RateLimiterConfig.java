package com.ApplicationSecurity.ApplicationSecurity.config;

import java.time.Duration;

import org.springframework.stereotype.Component;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

/**
 * Configuration class for Adding Rate Limiting for API's
 * @author akshataggarwal
 *
 */
@Component
public class RateLimiterConfig {
    private final Bucket bucket;

    public RateLimiterConfig() {
        Bandwidth limit = Bandwidth.classic(5, Refill.greedy(5, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder().addLimit(limit).build();
    }

    public boolean allowRequest() {
        return bucket.tryConsume(1);
    }
}