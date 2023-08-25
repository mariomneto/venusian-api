package com.mp.venusian.dtos;

import com.mp.venusian.enums.PostType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
    @NotNull
    @Enumerated(EnumType.STRING)
    private PostType postType;
    @NotBlank
    @Size(max = 280)
    private String content;
}