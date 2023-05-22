package com.mp.venusian.services;

import com.mp.venusian.models.Post.Post;
import com.mp.venusian.models.User;
import com.mp.venusian.repositories.PostRepository;
import com.mp.venusian.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Transactional
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public void deleteById(UUID id) {
        postRepository.deleteById(id);
    }
}
