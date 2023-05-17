package com.mp.venusian.models.Post;

import com.mp.venusian.models.Comment.Comment;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "posts")
public class Post implements Serializable {
    @Id
    private String id;
    @Column(nullable = false, length = 500)
    private String text;
    @Column(nullable = false)
    private String[] words;
    @Column(nullable = false)
    private int views;
    @Column(nullable = false)
    private Comment[] comments;
    @Embedded
    private OriginalPoster originalPoster;
    @Embedded
    private SocialSchema socialSchema;
}
