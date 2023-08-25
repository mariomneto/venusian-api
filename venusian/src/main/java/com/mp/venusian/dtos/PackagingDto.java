package com.mp.venusian.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PackagingDto {
    @NotBlank
    @Size(max = 60)
    private String type;
    @NotNull
    private float heightInCentimeters;
    @NotNull
    private float widthInCentimeters;
}
