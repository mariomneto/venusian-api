package com.mp.venusian.models.Comment;

import jakarta.persistence.Column;

public class ParentCommentModel {
    @Column(nullable = false)
    private String id;
    @Column(nullable = false)
    private String posterId;
}
