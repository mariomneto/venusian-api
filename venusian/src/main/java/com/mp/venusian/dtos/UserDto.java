package com.mp.venusian.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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