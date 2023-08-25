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

    @Column(nullable = false, columnDefinition = "DATE")
    private Date dateOfPurchase;
    @Column(nullable = false)
    private float totalPrice;
    @Column(nullable = false)
    private float shipping;
    @Column(nullable = false)
    private List<ProductPurchase> productPurchases;
    @Column(nullable = false)
    private float saleTargetValue;
    @Column(nullable = false)
    private float saleTargetValueWithTaxes;
    @Column(nullable = false)
    private float totalProjectedProfit;
    @Column(nullable = false)
    private float projectedProfitPercentage;
    @Column(nullable = false)
    private float optimisticTotalProfit;
    @Column(nullable = false)
    private float optimisticProfitPercentage;

}
