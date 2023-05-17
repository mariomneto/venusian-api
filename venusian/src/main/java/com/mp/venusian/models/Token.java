package com.mp.venusian.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Table(name = "tokens")
public class Token implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public String id;
    @Column(unique = true)
    public String token;
    public boolean revoked;
    public boolean expired;
}