package com.mp.venusian.models.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Poster {
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String threadId;
}
