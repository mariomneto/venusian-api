package com.mp.venusian.models.Response;

import com.mp.venusian.models.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TokenResponse {
    final String authToken;
    final String refreshToken;
}
