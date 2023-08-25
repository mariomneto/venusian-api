package com.mp.venusian.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;
@Data
public class ProjectionDto {
    @NotBlank
    private UUID userId;
    @NotNull
    private float exposure;
    @NotNull
    private float projectedProfitOnExposure;
    @NotNull
    private float projectedProfitOnExposurePercentage;
    @NotNull
    private float optimisticProjectedProfitOnExposurePercentage;
    @NotNull
    private float projectedReturn;
    @NotNull
    private float projectedReturnPercentage;
    @NotNull
    private float optimisticProjectedReturn;
    @NotNull
    private float optimisticProjectedReturnPercentage;
}
