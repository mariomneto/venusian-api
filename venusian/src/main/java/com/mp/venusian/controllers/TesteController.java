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
@RequestMapping("teste")
public class TesteController {
    @GetMapping
    public ResponseEntity<Object> teste(){
        return ResponseEntity.status(HttpStatus.OK).body("opa");
    }
}