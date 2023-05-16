package com.mp.venusian.models.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CommentModel {
    @Id
    private String id;
    @Column(nullable = false)
    private String parentPostId;
    @Column(nullable = false)
    private PosterModel poster;
    @Column(nullable = false, length = 300)
    private String text;
    @Column(nullable = false)
    private int likes;
    @Column
    private ParentCommentModel parentComment;
}
