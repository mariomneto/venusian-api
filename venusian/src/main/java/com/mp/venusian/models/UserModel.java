package com.mp.venusian.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "TB_USER")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private LocalDateTime registrationDate;
}