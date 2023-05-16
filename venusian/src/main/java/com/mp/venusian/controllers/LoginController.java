//package com.mp.venusian.controllers;
//
//import com.mp.venusian.dtos.UserLoginDto;
//import com.mp.venusian.enums.RegistrationType;
//import com.mp.venusian.models.User;
//import com.mp.venusian.services.UserService;
//import com.mp.venusian.util.JwtTokenUtil;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
//public class LoginController {
//    private final UserService userService;
//    private final JwtTokenUtil jwtTokenUtil;
//    private final AuthenticationManager authenticationManager;
//
//    public LoginController(UserService userService, JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
//        this.userService = userService;
//        this.jwtTokenUtil = jwtTokenUtil;
//        this.authenticationManager = authenticationManager;
//    }
//
//    @PostMapping("login")
//    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserLoginDto userLoginDto) {
//        String authentication = userLoginDto.getRegistrationType() == RegistrationType.PHONE ?
//                userLoginDto.getPhone() : userLoginDto.getEmail();
//        try {
//            Authentication authenticate = authenticationManager
//                    .authenticate(
//                            new UsernamePasswordAuthenticationToken(
//                                    authentication, userLoginDto.getPassword()
//                            )
//                    );
//
//            User user = (User) authenticate.getPrincipal();
//
//            return ResponseEntity.ok()
//                    .header(
//                            HttpHeaders.AUTHORIZATION,
//                            jwtTokenUtil.generateToken(user.getId())
//                    )
//                    .body(user);
//        } catch (BadCredentialsException ex) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
//}