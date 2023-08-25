package com.mp.venusian.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Data
public class PurchaseDto {
    @Size(max = 100)
    private String userId;
    @NotBlank
    @Size(max = 30)
    private String password;
    private float priceBrl;
    @NotBlank
    private String mlCode;
}

//    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    @Column(columnDefinition = "BINARY(16)")
//    private UUID id;
//    @Column(length = 100)
//    private String userId;
//    @Column
//    private float priceBrl;
//    @Column(nullable = false, length = 100)
//    private String mlCode;
//    @Column
//    private String accountEmail;
//    @Column
//    private String accountPassword;
//    @Column
//    private String latestCode;
//    @Column(nullable = false, columnDefinition = "DATE")
//    private Date lastCodeDate;
//    @Column(nullable = false, columnDefinition = "DATE")
//    private Date purchaseDate;