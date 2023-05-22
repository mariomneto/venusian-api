package com.mp.venusian.repositories;

import com.mp.venusian.models.AuthToken;
import com.mp.venusian.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    boolean existsById(UUID id);
    Optional<RefreshToken> findById(UUID id);
    Optional<RefreshToken> findByToken(String token);
    void deleteByUserId(UUID userId);
}
