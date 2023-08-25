package com.mp.venusian.controllers;

import com.mp.venusian.configuration.CustomAuthenticationProvider;
import com.mp.venusian.dtos.UserLoginDto;
import com.mp.venusian.dtos.UserRefreshDto;
import com.mp.venusian.dtos.UserRegisterDto;
import com.mp.venusian.models.*;
import com.mp.venusian.models.Response.AuthResponse;
import com.mp.venusian.models.Response.TokenResponse;
import com.mp.venusian.services.PasswordService;
import com.mp.venusian.services.AuthTokenService;
import com.mp.venusian.services.RefreshTokenService;
import com.mp.venusian.services.UserService;
import com.mp.venusian.util.JwtTokenUtil;
import com.mp.venusian.util.ValidationUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    final UserService userService;
    @Autowired
    final JwtTokenUtil jwtTokenUtil;
    @Autowired
    final PasswordService passwordService;
    @Autowired
    final AuthTokenService authTokenService;
    @Autowired
    final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    final RefreshTokenService refreshTokenService;
    @Autowired
    final CustomAuthenticationProvider authenticationProvider;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        if (userRegisterDto.getEmail() != null && userService.existsByEmail(userRegisterDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email already exists.");
        }
        if (userRegisterDto.getPhone() != null && userService.existsByPhone(userRegisterDto.getPhone())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this phone already exists.");
        }
        if (userRegisterDto.getEmail() != null && !ValidationUtil.validateEmail(userRegisterDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is invalid");
        }
        if (userRegisterDto.getPhone() != null && !ValidationUtil.validatePhone(userRegisterDto.getPhone())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Phone is invalid");
        }
        try {
            var user = storeNewUser(userRegisterDto);
            var body = new AuthResponse(generateNewAccessTokenForUser(user.getId()), user);
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        try {
            var credentials = new UsernamePasswordAuthenticationToken(userLoginDto.getLogin(),
                    userLoginDto.getPassword());
            Authentication authenticate = authenticationProvider.authenticate(credentials);
            User user = (User) authenticate.getPrincipal();
            authTokenService.deleteByUserId(user.getId());
            refreshTokenService.deleteByUserId(user.getId());
            var body = new AuthResponse(generateNewAccessTokenForUser(user.getId()), user);
            return ResponseEntity.ok().body(body);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<Object> refresh(@RequestBody @Valid UserRefreshDto userRefreshDto) {
        var authToken = userRefreshDto.getAuthToken();
        var refreshToken = userRefreshDto.getRefreshToken();
        final UUID userId = UUID.fromString(jwtTokenUtil.extractSubject(authToken));
        if (!jwtTokenUtil.isTokenValid(authToken, userId)) {
            authTokenService.deleteByUserId(userId);
            refreshTokenService.deleteByUserId(userId);
        }
        var body = generateNewAccessTokenForUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    private User storeNewUser(UserRegisterDto userRegisterDto) {
        var user = new User();
        BeanUtils.copyProperties(userRegisterDto, user);
        user.setRegistrationDate(new Date());
        var newUser = userService.save(user);
        var password = new Password();
        password.setUserId(newUser.getId());
        password.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        passwordService.save(password);
        return newUser;
    }

    private TokenResponse generateNewAccessTokenForUser(UUID userId) {
        var auth = new AuthToken();
        var generatedAuth = jwtTokenUtil.generateToken(userId);
        auth.setToken(generatedAuth.getToken());
        auth.setExpirationDate(generatedAuth.getExpiration());
        auth.setUserId(userId);
        authTokenService.save(auth).getToken();
        var refresh = new RefreshToken();
        var generatedRefresh = jwtTokenUtil.generateRefreshToken();
        refresh.setToken(generatedRefresh.getToken());
        refresh.setExpirationDate(generatedRefresh.getExpiration());
        refresh.setUserId(userId);
        refreshTokenService.save(refresh).getToken();
        return new TokenResponse(auth.getToken(), auth.getExpirationDate(), refresh.getToken(),
                refresh.getExpirationDate());
    }
}
