package com.mp.venusian.dtos.Post;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OriginalPosterDto {
    @NotBlank
    private String userId;
}