package com.mp.venusian.models.Comment;

import jakarta.persistence.Column;

public class PosterModel {
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String threadId;
}
