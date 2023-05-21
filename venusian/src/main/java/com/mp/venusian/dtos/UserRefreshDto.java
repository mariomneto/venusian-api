package com.mp.venusian.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRefreshDto {
    @NotBlank
    private String authToken;
    @NotBlank
    private String refreshToken;
}