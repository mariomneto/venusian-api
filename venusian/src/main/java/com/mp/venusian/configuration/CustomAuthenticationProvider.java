package com.mp.venusian.configuration;

import com.mp.venusian.models.Password;
import com.mp.venusian.models.User;
import com.mp.venusian.services.PasswordService;
import com.mp.venusian.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    final UserService userService;
    @Autowired
    final PasswordService passwordService;
    @Autowired
    final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String requestLogin = authentication.getName();
        String requestPassword = authentication.getCredentials().toString();

        Optional<User> optionalUser = userService.findByEmailOrPhone(requestLogin);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = optionalUser.get();

        Optional<Password> optionalPassword = passwordService.findByUserId(user.getId());
        if (optionalPassword.isEmpty()) {
            throw new UsernameNotFoundException("Password not found");
        }
        String userPassword = optionalPassword.get().getPassword();
        boolean isAuthenticated = passwordEncoder.matches(requestPassword, userPassword);

        if (!isAuthenticated) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}