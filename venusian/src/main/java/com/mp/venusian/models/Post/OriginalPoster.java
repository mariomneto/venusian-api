package com.mp.venusian.models.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Embeddable
@Data
public class OriginalPoster {
    @Column(nullable = false)
    private UUID userId;
    @Column(nullable = false)
    private String threadId;
    @Column(nullable = false)
    private String threadColor;
}
