package com.mp.venusian.dtos.Comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ParentCommentDto {
    @NotBlank
    private String id;
    @NotBlank
    private String posterId;
}
