package com.mp.venusian.models.Comment;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ParentCommentModel {
    @Column(nullable = false)
    private String id;
    @Column(nullable = false)
    private String posterId;
}
