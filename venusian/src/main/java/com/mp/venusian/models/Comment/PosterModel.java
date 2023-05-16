package com.mp.venusian.models.Comment;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PosterModel {
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String threadId;
}
