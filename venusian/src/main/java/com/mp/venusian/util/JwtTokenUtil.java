package com.mp.venusian.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.security.Key;
import java.util.*;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
    private static final Key PRIVATE_KEY = getKey();
    private static final long AUTH_EXPIRATION = 1800000;

    public String generateToken(UUID userId) {
        return generateToken(new HashMap<>(), userId);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UUID userId
    ) {
        return buildToken(extraClaims, userId.toString(), AUTH_EXPIRATION);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            String userId,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(PRIVATE_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken() {
        String subject = UUID.randomUUID().toString();
        Date expiration = Calendar.getInstance().getTime();
        expiration.setMonth(expiration.getMonth() + 1);
        return  Jwts
                .builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiration)
                .signWith(PRIVATE_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        try{
            return Jwts
                    .parserBuilder()
                    .setSigningKey(PRIVATE_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public boolean isTokenValid(String token, UUID userId) {
        final UUID tokenUserId = UUID.fromString(extractSubject(token));
        return (tokenUserId.equals(userId) && isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return new Date().before(extractExpiration(token));
    }

    public static Key getKey() {
        String fileName = "pvt/jwtSigningKey.json";
        InputStream inputStream;
        try{
            Resource resource = new ClassPathResource(fileName);
            inputStream = resource.getInputStream();
        } catch (Exception e) {
            throw new RuntimeException("JWT signing key not found on " + fileName);
        }
        Scanner scanner = new Scanner(inputStream);
        String jsonString = scanner.useDelimiter("\\A").next();
        JSONObject jsonObject = new JSONObject(jsonString);
        String key = jsonObject.getString("key");
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
