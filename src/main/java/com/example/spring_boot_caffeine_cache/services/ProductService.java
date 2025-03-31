package com.example.spring_boot_caffeine_cache.services;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Cacheable(value = "productCache", key = "#id")
    public String getProductById(String id) {
        simulateSlowService();
        return "Product " + id;
    }

    // Simulate a delay to represent a slow database call
    private void simulateSlowService() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // private CompletableFuture<String> loadProductAsync(Object key) {
    //     return CompletableFuture.supplyAsync(() -> {
    //         // Simulate slow service
    //         simulateSlowService();
    //         return "Product " + key;
    //     });
    // }

    @CacheEvict(value = "productCache", key = "#id")
    public void updateProduct(String id, String newProductData) {
        // Code to update the product in the database (omitted for brevity)
    }

    @CachePut(value = "productCache", key = "#id")
    public String updateProductInCache(String id, String newProductData) {
        return newProductData; // This updates the cache for the specific product ID
    }
}
