package com.mp.venusian.dtos.Post;

import com.mp.venusian.enums.PostType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SocialSchemaDto {
    @NotBlank
    @Enumerated(EnumType.STRING)
    private PostType postType;
    private String privateMessageUserId;
}
