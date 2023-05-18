package com.mp.venusian.controllers;

import com.mp.venusian.configs.CustomAuthenticationProvider;
import com.mp.venusian.dtos.UserLoginDto;
import com.mp.venusian.enums.RegistrationType;
import com.mp.venusian.models.User;
import com.mp.venusian.services.UserService;
import com.mp.venusian.util.JwtTokenUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("login")
public class LoginController {
    @Autowired
    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    private final CustomAuthenticationProvider authenticationProvider;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        try {
            Authentication authenticate = authenticationProvider
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    userLoginDto.getLogin(), userLoginDto.getPassword()
                            )
                    );

            User user = (User) authenticate.getPrincipal();

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtTokenUtil.generateToken(user.getId())
                    )
                    .body(user);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}