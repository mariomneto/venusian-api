package com.mp.venusian.repositories;

import com.mp.venusian.models.Packaging;
import com.mp.venusian.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PackagingRepository extends JpaRepository<Packaging, UUID> {
    void deleteById(UUID id);
    Optional<Packaging> findById(UUID id);
    List<Packaging> findByType(String type);
}
