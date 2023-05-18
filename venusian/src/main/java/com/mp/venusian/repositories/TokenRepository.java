package com.mp.venusian.repositories;

import com.mp.venusian.models.Token;
import com.mp.venusian.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {
    boolean existsById(UUID id);
    Optional<Token> findById(UUID id);
    Optional<Token> findByToken(String token);
    List<Token> findByUserIdAndRevokedFalseAndExpiredFalse(UUID userId);
}
