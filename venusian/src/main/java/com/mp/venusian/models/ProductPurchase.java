package com.mp.venusian.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "productPurchase")
public class ProductPurchase implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(nullable = false)
    private UUID productId;
    @Column(nullable = false)
    private UUID purchaseId;
    @Column(nullable = false)
    private String supplierUrl;
    @Column(nullable = false)
    private float totalPrice;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private float unitValue;
    @Column(nullable = false)
    private float unitValueWithTaxes;
    @Column(nullable = false)
    private float saleTargetValue;
    @Column(nullable = false)
    private float saleTargetValueWithTaxes;
    @Column(nullable = false)
    private float unitProjectedProfit;
    @Column(nullable = false)
    private float totalProjectedProfit;
    @Column(nullable = false)
    private float projectedProfitPercentage;
    @Column(nullable = false)
    private float optimisticUnitProfit;
    @Column(nullable = false)
    private float optimisticTotalProfit;
    @Column(nullable = false)
    private float optimisticProfitPercentage;

}
