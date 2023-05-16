package com.mp.venusian.models;

import com.google.cloud.Timestamp;
import com.mp.venusian.enums.RegistrationType;
import com.mp.venusian.enums.Role;
import com.mp.venusian.models.Comment.CommentModel;
import com.mp.venusian.models.Post.PostModel;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class User implements Serializable {
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
    @Enumerated(EnumType.STRING)
    private RegistrationType registrationType;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    private Timestamp registrationDate;
    @Column(nullable = false)
    private PostModel posts[];
    @Column(nullable = false)
    private CommentModel comments[];
    @Column(nullable = false)
    private String[] friends;
    @Column
    private List<Token> tokens;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}