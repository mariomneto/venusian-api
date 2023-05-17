package com.mp.venusian.models;

import com.mp.venusian.enums.RegistrationType;
import com.mp.venusian.enums.Role;
import com.mp.venusian.models.Comment.Comment;
import com.mp.venusian.models.Post.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
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
//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private Role role;
//    @Column(nullable = false)
//    private Date registrationDate;
//    @Column(nullable = false)
//    private Post posts[];
//    @Column(nullable = false)
//    private Comment comments[];
//    @Column(nullable = false)
//    private String[] friends;
//    @Column
//    private List<Token> tokens;

    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
        return List.of(new SimpleGrantedAuthority("USER")); //fix
    }
}