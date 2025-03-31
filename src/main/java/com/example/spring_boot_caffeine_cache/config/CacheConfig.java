package com.example.spring_boot_caffeine_cache.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
public class CacheConfig {

    @Bean
    public CaffeineCacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("productCache");
        cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;
    }

    Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .maximumSize(100);
    }
    // Caffeine<Object, Object> caffeineCacheBuilder() {
    //     return Caffeine.newBuilder()
    //             .expireAfterAccess(5, TimeUnit.MINUTES)
    //             .maximumSize(100)
    //             .buildAsync((key) -> loadProductAsync(key));
    // }
}
