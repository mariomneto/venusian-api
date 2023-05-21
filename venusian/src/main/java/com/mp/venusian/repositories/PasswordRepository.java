package com.mp.venusian.repositories;

import com.mp.venusian.models.Password;
import com.mp.venusian.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PasswordRepository extends JpaRepository<Password, UUID> {
    Optional<Password> findByUserId(UUID userId);
//    @Modifying
//    void changePasswordWhereUserId(String password, UUID userId);
}