package com.mp.venusian.services;

import com.mp.venusian.models.Token;
import com.mp.venusian.repositories.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TokenService {
    @Autowired
    final TokenRepository tokenRepository;

    @Transactional
    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    public Optional<Token> findById(UUID id) {
        return tokenRepository.findById(id);
    }

    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public List<Token> findAllValidTokenByUser(UUID userId) {
        return tokenRepository.findByUserIdAndRevokedFalseAndExpiredFalse(userId);
    }
}