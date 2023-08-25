package com.mp.venusian.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginDto {
    @NotBlank
    @Size(max = 50)
    private String login;
    @NotBlank
    @Size(max = 30)
    private String password;
}