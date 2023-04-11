package com.mp.venusian.dtos.Comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CommentDto {
    @NotBlank
    private String parentPostId;
    @NotBlank
    private PosterDto poster;
    @NotBlank
    @Size(max = 300)
    private String text;
    @NotBlank
    private ParentCommentDto parentComment;
}
