package com.mp.venusian.controllers;

import com.mp.venusian.dtos.UserRegisterDto;
import com.mp.venusian.enums.RegistrationType;
import com.mp.venusian.models.Token;
import com.mp.venusian.models.User;
import com.mp.venusian.services.TokenService;
import com.mp.venusian.services.UserService;
import com.mp.venusian.util.JwtTokenUtil;
import com.mp.venusian.util.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Date;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("register")
public class RegisterController {
    @Autowired
    final UserService userService;
    @Autowired
    final TokenService tokenService;
    @Autowired
    final JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        if(userRegisterDto.getEmail() != null && userService.existsByEmail(userRegisterDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email already exists.");
        }
        if(userRegisterDto.getPhone() != null && userService.existsByPhone(userRegisterDto.getPhone())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this phone already exists.");
        }
        if(userRegisterDto.getRegistrationType() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User needs a registration type.");
        }
        if(userRegisterDto.getRegistrationType() == RegistrationType.EMAIL && userRegisterDto.getEmail() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User needs an email.");
        }
        if(userRegisterDto.getRegistrationType() == RegistrationType.PHONE && userRegisterDto.getPhone() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User needs a phone.");
        }
        if(userRegisterDto.getEmail() != null && !Test.testEmail(userRegisterDto.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is invalid");
        }
        if(userRegisterDto.getPhone() != null && !Test.testPhone(userRegisterDto.getPhone())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Phone is invalid");
        }
        var user = new User();
        BeanUtils.copyProperties(userRegisterDto, user);
        user.setRegistrationDate(new Date());
        try {
            var newUser = userService.save(user);
            var token = new Token();
            token.setToken(jwtTokenUtil.generateToken(newUser.getId()));
            token.setUserId(newUser.getId());
            tokenService.save(token);
            return ResponseEntity.status(HttpStatus.CREATED)
                .header(
                    HttpHeaders.AUTHORIZATION,
                    token.getToken()
                )
                .body(newUser);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}