package com.mp.venusian.models.Post;

import com.mp.venusian.models.Comment.CommentModel;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class PostModel {
    @Id
    private String id;
    @Column(nullable = false)
    private OriginalPosterModel originalPoster;
    @Column(nullable = false, length = 500)
    private String text;
    @Column(nullable = false)
    private String[] words;
    @Column(nullable = false)
    private int views;
    @Column(nullable = false)
    private CommentModel[] comments;
}
