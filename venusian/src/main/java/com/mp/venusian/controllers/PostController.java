package com.mp.venusian.controllers;

import com.mp.venusian.dtos.PostDto;
import com.mp.venusian.mappers.PostMapper;
import com.mp.venusian.models.Post.Post;
import com.mp.venusian.models.Response.AuthResponse;
import com.mp.venusian.models.User;
import com.mp.venusian.services.PostService;
import com.mp.venusian.services.UserService;
import com.mp.venusian.util.HeaderUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    @Autowired
    final PostService postService;
    @Autowired
    final UserService userService;
    @Autowired
    final HeaderUtil headerUtil;
    @Autowired
    final PostMapper postMapper;
    @PostMapping
    public ResponseEntity<Object> post(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, @RequestBody @Valid PostDto postDto) {
        var userId = headerUtil.getUserIdFromAuthHeader(authHeader);
        if(userId.isPresent()) {
            Optional<User> user = userService.findById(userId.get());
            if(user.isPresent()){
                try{
                    var mapped = postMapper.mapPostDtoToPost(postDto, userId.get());
                    Post post = postService.save(mapped);
                    return ResponseEntity.status(HttpStatus.CREATED).body(post);
                }
                catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed sending post.");
    }
    @GetMapping
    public ResponseEntity<Object> getPosts(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        var userId = headerUtil.getUserIdFromAuthHeader(authHeader);
        if(userId.isPresent()) {
            //get user posts
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }
}
