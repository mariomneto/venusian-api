package com.mp.venusian.dtos.Comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PosterDto {
    @NotBlank
    private String userId;
}
