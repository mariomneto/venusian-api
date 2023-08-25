package com.mp.venusian.services;

import com.mp.venusian.models.Product;
import com.mp.venusian.models.ProductPurchase;
import com.mp.venusian.repositories.ProductPurchaseRepository;
import com.mp.venusian.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductPurchaseService {
    @Autowired
    final ProductPurchaseRepository productPurchaseRepository;
    @Transactional
    public ProductPurchase save(ProductPurchase productPurchase) {
        return productPurchaseRepository.save(productPurchase);
    }
    @Transactional
    public void deleteById(UUID id) {
        productPurchaseRepository.deleteById(id);
    }
    public Optional<ProductPurchase> findById(UUID id) {
        return productPurchaseRepository.findById(id);
    }
    public List<ProductPurchase> findByProductId(UUID productId) {
        return productPurchaseRepository.findByProductId(productId);
    }
}
