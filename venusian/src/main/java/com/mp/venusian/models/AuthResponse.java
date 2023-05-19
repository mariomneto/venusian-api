package com.mp.venusian.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthResponse {
    final Token authToken;
    final Token refreshToken;
    final User user;
}
