package com.mp.venusian.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(nullable = false, length = 200)
    private String name;
    @Column(nullable = false)
    private boolean active;
    @Column(nullable = false)
    private int itemsAvailable;
    @Column(nullable = false)
    private int totalBought;
    @Column(nullable = false)
    private int itemsSold;
}
