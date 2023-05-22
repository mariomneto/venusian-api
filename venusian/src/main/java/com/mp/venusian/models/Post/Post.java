package com.mp.venusian.models.Post;

import com.mp.venusian.enums.PostType;
import com.mp.venusian.models.Comment.Comment;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "post")
public class Post implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(nullable = false, length = 280)
    private String content;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PostType postType;
    @Column(nullable = false)
    private int views;
    @Embedded
    private OriginalPoster originalPoster;
//    @Column(nullable = false)
//    private String[] words;
//    @Column(nullable = false)
//    private Comment[] comments;
}
