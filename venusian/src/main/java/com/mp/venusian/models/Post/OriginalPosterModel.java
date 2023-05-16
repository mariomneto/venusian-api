package com.mp.venusian.models.Post;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class OriginalPosterModel {
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String threadId;
}
