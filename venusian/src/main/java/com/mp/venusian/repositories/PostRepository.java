package com.mp.venusian.repositories;

import com.mp.venusian.models.Post.Post;
import com.mp.venusian.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    boolean existsById(UUID id);
    Optional<Post> findById(UUID id);
}
