package com.mp.venusian.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "projection")
public class Projection implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(nullable = false)
    private UUID userId;
    @Column(nullable = false)
    private float exposure;
    @Column(nullable = false)
    private float projectedProfitOnExposure;
    @Column(nullable = false)
    private float projectedProfitOnExposurePercentage;
    @Column(nullable = false)
    private float optimisticProjectedProfitOnExposurePercentage;
    @Column(nullable = false)
    private float projectedReturn;
    @Column(nullable = false)
    private float projectedReturnPercentage;
    @Column(nullable = false)
    private float optimisticProjectedReturn;
    @Column(nullable = false)
    private float optimisticProjectedReturnPercentage;
}
