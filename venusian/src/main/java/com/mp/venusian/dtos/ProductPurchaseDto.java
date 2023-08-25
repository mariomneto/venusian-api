package com.mp.venusian.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductPurchaseDto {
    @NotBlank
    private UUID productId;
    @NotBlank
    private UUID purchaseId;
    @NotBlank
    private String supplierUrl;
    @NotNull
    private float totalPrice;
    @NotNull
    private Integer quantity;
    @NotNull
    private float saleTargetValue;
}
