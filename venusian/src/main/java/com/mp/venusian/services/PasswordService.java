package com.mp.venusian.services;

import com.mp.venusian.models.Password;
import com.mp.venusian.repositories.PasswordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordService {
    @Autowired
    PasswordRepository passwordRepository;

    @Transactional
    public Password save(Password password) {
        return passwordRepository.save(password);
    }

    public Optional<Password> findByUserId(UUID userId) {
        return passwordRepository.findByUserId(userId);
    }

//    public void changePasswordWhereUserId(String password, UUID userId) {
//        passwordRepository.changePasswordWhereUserId(password, userId);
//    }
}