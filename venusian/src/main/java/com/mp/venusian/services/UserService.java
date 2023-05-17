package com.mp.venusian.services;

import com.mp.venusian.models.User;
import com.mp.venusian.repositories.UserRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

//    public Optional<User> findByEmailOrPhone(String value) {
//        return userRepository.findByEmailOrPhone(value);
//    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

//    @Transactional
//    public void delete(UserModel userModel) {
//        userRepository.delete(userModel);
//    }
}