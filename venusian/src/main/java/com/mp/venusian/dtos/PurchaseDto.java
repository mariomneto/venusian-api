package com.mp.venusian.dtos;

import com.mp.venusian.models.ProductPurchase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PurchaseDto {
    @NotNull
    private Date dateOfPurchase;
    @NotBlank
    private float totalPrice;
    @NotBlank
    private float shipping;
    @NotBlank
    private List<ProductPurchase> productPurchases;
}
