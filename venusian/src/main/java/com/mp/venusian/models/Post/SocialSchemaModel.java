package com.mp.venusian.models.Post;

import com.mp.venusian.enums.PostType;
import jakarta.persistence.Column;

public class SocialSchemaModel {
    @Column(nullable = false)
    private PostType postType;
    @Column
    private String privateMessageUserId;
}
