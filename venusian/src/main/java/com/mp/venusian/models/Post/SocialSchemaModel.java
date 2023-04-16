package com.mp.venusian.models.Post;

import com.mp.venusian.enums.PostType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class SocialSchemaModel {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PostType postType;
    @Column
    private String privateMessageUserId;
}
