package com.mp.venusian.dtos.Post;

import com.mp.venusian.enums.PostType;
import jakarta.validation.constraints.NotBlank;

public class SocialSchemaDto {
    @NotBlank
    private PostType postType;
    private String privateMessageUserId;
    private String[] bubbleUserIds;
}
