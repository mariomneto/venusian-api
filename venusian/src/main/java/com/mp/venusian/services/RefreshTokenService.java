package com.mp.venusian.services;

import com.mp.venusian.models.RefreshToken;
import com.mp.venusian.repositories.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    @Autowired
    final RefreshTokenRepository refreshTokenRepository;
    @Transactional
    public RefreshToken save(RefreshToken token) {
        return refreshTokenRepository.save(token);
    }
    @Transactional
    public void deleteByUserId(UUID userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }
    public Optional<RefreshToken> findById(UUID id) {
        return refreshTokenRepository.findById(id);
    }
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
}