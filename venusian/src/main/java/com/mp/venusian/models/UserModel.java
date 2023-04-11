package com.mp.venusian.models;

import com.google.cloud.Timestamp;
import com.mp.venusian.enums.RegistrationType;
import com.mp.venusian.models.Comment.CommentModel;
import com.mp.venusian.models.Post.PostModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter @Setter
public class UserModel implements Serializable {
    @Id
    private String id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(unique = true, length = 50)
    private String email;
    @Column(unique = true, length = 11)
    private String phone;
    @Column(nullable = false, length = 30)
    private String password;
    @Column(nullable = false)
    private RegistrationType registrationType;
    @Column(nullable = false)
    private Timestamp registrationDate;
    @Column(nullable = false)
    private PostModel posts[];
    @Column(nullable = false)
    private CommentModel comments[];
    @Column(nullable = false)
    private String[] friends;
}