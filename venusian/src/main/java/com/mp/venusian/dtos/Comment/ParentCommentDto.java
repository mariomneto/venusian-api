package com.mp.venusian.dtos.Comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ParentCommentDto {
    @NotBlank
    private String id;
    @NotBlank
    private String posterId;
}
