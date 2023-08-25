package com.mp.venusian.services;

import com.mp.venusian.models.Product;
import com.mp.venusian.models.Purchase;
import com.mp.venusian.repositories.ProductRepository;
import com.mp.venusian.repositories.PurchaseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    @Autowired
    final PurchaseRepository purchaseRepository;
    @Transactional
    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
    @Transactional
    public void deleteById(UUID id) {
        purchaseRepository.deleteById(id);
    }
    public Optional<Purchase> findById(UUID id) {
        return purchaseRepository.findById(id);
    }
}
