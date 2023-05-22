package com.mp.venusian.configuration;

import com.mp.venusian.models.User;
import com.mp.venusian.services.AuthTokenService;
import com.mp.venusian.services.RefreshTokenService;
import com.mp.venusian.services.UserService;
import com.mp.venusian.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import java.util.Optional;
import java.util.UUID;

public class CustomLogoutHandler implements LogoutHandler {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserService userService;
    @Autowired
    AuthTokenService authTokenService;
    @Autowired
    RefreshTokenService refreshTokenService;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        final String token = authHeader.substring(7);
        final UUID userId = UUID.fromString(jwtTokenUtil.extractSubject(token));

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            boolean isTokenValid = false;
            Optional<User> userRequest = this.userService.findById(userId);
            if(userRequest.isPresent()){
                var userToken = authTokenService.findByToken(token);
                if(userToken.isPresent()) {
                    isTokenValid = jwtTokenUtil.isTokenValid(userToken.get().getToken(), userId);
                }
                if(isTokenValid) {
                    authTokenService.deleteByUserId(userId);
                    refreshTokenService.deleteByUserId(userId);
                    SecurityContextHolder.clearContext();
                    response.setStatus(HttpStatus.OK.value());
                    return;
                }
            }
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }
}
