package com.mp.venusian.configs;

import com.mp.venusian.models.User;
import com.mp.venusian.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    @Autowired
    public CustomAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String auth = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<User> optionalUser = userService.findByEmailOrPhone(auth);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = optionalUser.get();

        boolean isAuthenticated = user.getPassword().equals(password);

        if (!isAuthenticated) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return new UsernamePasswordAuthenticationToken(user.getId(), null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}