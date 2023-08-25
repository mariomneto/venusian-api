package com.mp.venusian.mappers;

import com.mp.venusian.dtos.PostDto;
import com.mp.venusian.models.Post.OriginalPoster;
import com.mp.venusian.models.Post.Post;
import com.mp.venusian.util.PostUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PostMapper {
    @Autowired
    PostUtil postUtil;
    public Post mapPostDtoToPost(PostDto postDto, UUID userId) {
        Post post = new Post();
        post.setContent(postDto.getContent());
        post.setPostType(postDto.getPostType());
        post.setViews(0);

        OriginalPoster originalPoster = new OriginalPoster();
        originalPoster.setUserId(userId);
        originalPoster.setThreadId(postUtil.generateRandomId());
        originalPoster.setThreadColor(postUtil.generateRandomColor());
        post.setOriginalPoster(originalPoster);

        return post;
    }
}
