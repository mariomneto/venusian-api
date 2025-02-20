package com.mp.venusian.models.Response;

import com.mp.venusian.models.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthResponse {
    final String authToken;
    final String refreshToken;
    final User user;
}
