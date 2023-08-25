package com.mp.venusian.services;

import com.mp.venusian.models.Packaging;
import com.mp.venusian.models.ProductPurchase;
import com.mp.venusian.repositories.PackagingRepository;
import com.mp.venusian.repositories.ProductPurchaseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PackagingService {
    @Autowired
    final PackagingRepository packagingRepository;
    @Transactional
    public Packaging save(Packaging packaging) {
        return packagingRepository.save(packaging);
    }
    @Transactional
    public void deleteById(UUID id) {
        packagingRepository.deleteById(id);
    }
    public Optional<Packaging> findById(UUID id) {
        return packagingRepository.findById(id);
    }
    public List<Packaging> findByType(String type) {
        return packagingRepository.findByType(type);
    }
}
