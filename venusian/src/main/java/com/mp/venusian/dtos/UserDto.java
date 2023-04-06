package com.mp.venusian.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {
    @NotBlank
    @Size(max = 100)
    private String name;
    @NotBlank
    @Size(max = 50)
    private String email;
    @Size(max = 11)
    private String phone;
    @NotBlank
    @Size(max = 30)
    private String password;
}