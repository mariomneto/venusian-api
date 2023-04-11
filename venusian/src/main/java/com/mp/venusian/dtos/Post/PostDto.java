package com.mp.venusian.dtos.Post;

import com.mp.venusian.enums.RegistrationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostDto {
    @NotBlank
    private OriginalPosterDto originalPoster;
    @Size(max = 500)
    private String text;
    @NotBlank SocialSchemaDto socialSchema;
}