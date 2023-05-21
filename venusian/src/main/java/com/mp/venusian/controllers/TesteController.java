package com.mp.venusian.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/teste")
public class TesteController {
    @GetMapping
    public ResponseEntity<Object> teste(){
        return ResponseEntity.status(HttpStatus.OK).body("autenticado");
    }
}