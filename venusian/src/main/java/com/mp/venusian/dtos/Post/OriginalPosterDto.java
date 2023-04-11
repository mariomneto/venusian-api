package com.mp.venusian.dtos.Post;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OriginalPosterDto {
    @NotBlank
    private String userId;
}