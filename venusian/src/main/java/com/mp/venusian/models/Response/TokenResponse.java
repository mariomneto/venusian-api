package com.mp.venusian.models.Response;

import com.mp.venusian.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
public class TokenResponse {
    final String authToken;
    final Date authExpiration;
    final String refreshToken;
    final Date refreshExpiration;
}
