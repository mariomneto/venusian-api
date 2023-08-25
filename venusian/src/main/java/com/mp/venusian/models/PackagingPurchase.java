package com.mp.venusian.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
@Table(name = "packagingPurchase")
public class PackagingPurchase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(nullable = false)
    private UUID packagingId;
    @Column(nullable = false)
    private float totalPrice;
    @Column(nullable = false)
    private float discount;
    @Column(nullable = false)
    private float shipping;
    @Column(nullable = false)
    private int unitsPerLot;
    @Column(nullable = false)
    private int lotsBought;
    @Column
    private int totalUnits;
    @Column(nullable = false)
    private float unitPrice;
}
