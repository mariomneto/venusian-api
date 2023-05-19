package com.mp.venusian.models;

import com.mp.venusian.enums.TokenType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "tokens")
public class Token implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(nullable = false)
    private UUID userId;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private TokenType tokenType;
    @Column
    private boolean revoked = false;
    @Column
    private boolean expired = false;
}