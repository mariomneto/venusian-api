package com.mp.venusian.models.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;


@Embeddable
@Data
public class ParentComment {
    @Column(nullable = false)
    private String commentId;
    @Column(nullable = false)
    private String posterId;
}
