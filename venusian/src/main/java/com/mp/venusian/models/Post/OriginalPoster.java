package com.mp.venusian.models.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import org.springframework.stereotype.Component;

@Embeddable
@Data
public class OriginalPoster {
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String threadId;
}
