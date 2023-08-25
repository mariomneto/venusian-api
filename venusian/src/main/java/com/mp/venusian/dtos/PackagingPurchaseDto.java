package com.mp.venusian.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class PackagingPurchaseDto {
    @NotBlank
    private UUID packagingId;
    @NotNull
    private float totalPrice;
    @NotNull
    private float discount;
    @NotNull
    private float shipping;
    @NotNull
    private Integer unitsPerLot;
    @NotNull
    private Integer lotsBought;
    @NotBlank
    private String url;
}
