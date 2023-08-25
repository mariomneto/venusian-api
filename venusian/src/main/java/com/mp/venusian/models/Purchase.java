package com.mp.venusian.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "purchase")
public class Purchase implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(length = 100)
    private String userId;
    @Column
    private float priceBrl;
    @Column(nullable = false, length = 100)
    private String mlCode;
    @Column
    private String accountEmail;
    @Column
    private String accountPassword;
    @Column
    private String latestCode;
    @Column(nullable = false, columnDefinition = "DATE")
    private Date lastCodeDate;
    @Column(nullable = false, columnDefinition = "DATE")
    private Date purchaseDate;
}