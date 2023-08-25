package com.mp.venusian.repositories;

import com.mp.venusian.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    void deleteById(UUID id);
    Optional<Product> findById(UUID id);
    Optional<Product> findByName(String name);
}
