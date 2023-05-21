package com.mp.venusian.repositories;

import com.mp.venusian.models.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, UUID> {
    boolean existsById(UUID id);
    Optional<AuthToken> findById(UUID id);
    Optional<AuthToken> findByToken(String token);
    void deleteByUserId(UUID userId);
}
