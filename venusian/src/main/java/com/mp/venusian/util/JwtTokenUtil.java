package com.mp.venusian.util;

import io.jsonwebtoken.Claims;
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
    private static final long EXPIRATION = 86400000;
    private static final long REFRESH_EXPIRATION = 604800000;
    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UUID userId) {
        return generateToken(new HashMap<>(), userId);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UUID userId
    ) {
        return buildToken(extraClaims, userId.toString(), EXPIRATION);
    }

    public String generateRefreshToken(
            String userId
    ) {
        return buildToken(new HashMap<>(), userId, REFRESH_EXPIRATION);
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

    public boolean isTokenValid(String token, UUID userId) {
        final UUID tokenId = UUID.fromString(extractSubject(token));
        return (tokenId.equals(userId)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(PRIVATE_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
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
        //System.out.println("jsonString " + jsonString);
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
