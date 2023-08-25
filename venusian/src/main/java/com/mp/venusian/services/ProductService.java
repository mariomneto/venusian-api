package com.mp.venusian.services;

import com.mp.venusian.models.Product;
import com.mp.venusian.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    final ProductRepository productRepository;
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }
    @Transactional
    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }
    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }
}
