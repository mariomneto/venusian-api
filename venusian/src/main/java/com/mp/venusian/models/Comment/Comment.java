package com.mp.venusian.models.Comment;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String parentPostId;
    @Column(nullable = false, length = 300)
    private String text;
    @Column(nullable = false)
    private int likes;
    @Embedded
    private Poster poster;
    @Embedded
    private ParentComment parentComment;
}
