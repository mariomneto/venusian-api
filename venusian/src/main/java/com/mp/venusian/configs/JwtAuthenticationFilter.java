package com.mp.venusian.configs;

import com.mp.venusian.models.User;
import com.mp.venusian.util.JwtTokenUtil;
import com.mp.venusian.services.TokenService;
import com.mp.venusian.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtService;
    private final UserService userService;
    private final TokenService tokenService;
    private final JwtTokenUtil jwtTokenUtil;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String token = authHeader.substring(7);
        final String userId = jwtService.extractSubject(token);
        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            boolean isTokenValid;
            User user;
            Optional<User> userRequest = this.userService.findById(userId);
            if(userRequest.isPresent()){
                user = userRequest.get();
                isTokenValid = tokenService.findById(token)
                        .map(t -> !t.isExpired() && !t.isRevoked())
                        .orElse(false) && jwtService.isTokenValid(token, userId);

                if(isTokenValid) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userId, null, user.getAuthorities()
                    );
                    authentication.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
