package com.mp.venusian.repositories;

import com.mp.venusian.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findById(UUID id);
    boolean existsById(UUID id);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
//    Optional<User> findByEmailOrPhone(String value);
}
