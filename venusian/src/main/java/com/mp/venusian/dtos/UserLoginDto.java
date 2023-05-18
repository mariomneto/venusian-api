package com.mp.venusian.dtos;

import com.mp.venusian.enums.RegistrationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginDto {
    @Size(max = 50)
    private String login;
    @NotBlank
    @Size(max = 30)
    private String password;
}