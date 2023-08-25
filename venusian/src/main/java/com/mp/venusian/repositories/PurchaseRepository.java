package com.mp.venusian.repositories;

import com.mp.venusian.models.Product;
import com.mp.venusian.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
    void deleteById(UUID id);
    Optional<Purchase> findById(UUID id);
}
