package com.mp.venusian.repositories;

import com.mp.venusian.models.Product;
import com.mp.venusian.models.ProductPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductPurchaseRepository extends JpaRepository<ProductPurchase, UUID> {
    void deleteById(UUID id);
    Optional<ProductPurchase> findById(UUID id);
    List<ProductPurchase> findByProductId(UUID id);
}
