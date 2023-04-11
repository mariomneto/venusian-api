package com.mp.venusian.dtos.Comment;

import jakarta.validation.constraints.NotBlank;

public class PosterDto {
    @NotBlank
    private String userId;
}
