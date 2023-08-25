package com.mp.venusian.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
public class TokenExpiration {
    String token;
    Date expiration;
}
