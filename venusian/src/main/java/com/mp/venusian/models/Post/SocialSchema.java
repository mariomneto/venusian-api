package com.mp.venusian.models.Post;

import com.mp.venusian.enums.PostType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.stereotype.Component;

@Embeddable
@Data
public class SocialSchema {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PostType postType;
    @Column
    private String privateMessageUserId;
}
