package com.mp.venusian.controllers;

import com.mp.venusian.dtos.UserRegisterDto;
import com.mp.venusian.enums.RegistrationType;
import com.mp.venusian.models.User;
import com.mp.venusian.services.UserService;
import com.mp.venusian.util.JwtTokenUtil;
import com.mp.venusian.util.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Date;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/register")
public class RegisterController {
    final UserService userService;
    final JwtTokenUtil jwtTokenUtil;

    public RegisterController(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        if(userService.existsByEmail(userRegisterDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email already exists.");
        }
        if(userService.existsByPhone(userRegisterDto.getPhone())){
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
        var userModel = new User();
        BeanUtils.copyProperties(userRegisterDto, userModel);
//        userModel.setRegistrationDate(new Date());
        try {
            User newUser = userService.save(userModel);
            return ResponseEntity.status(HttpStatus.CREATED)
                .header(
                    HttpHeaders.AUTHORIZATION,
                    jwtTokenUtil.generateToken(newUser.getId())
                )
                .body(newUser);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Object> getUser(@PathVariable(value = "id") String id){
//        Optional<UserModel> optionalUserModel = userService.findById(id);
//        if (!optionalUserModel.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(optionalUserModel.get());
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id){
//        Optional<UserModel> optionalUserModel = userService.findById(id);
//        if (!optionalUserModel.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
//        }
//        userService.delete(optionalUserModel.get());
//        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id, @RequestBody @Valid UserDto userDto) throws InterruptedException, ExecutionException {
//        Optional<UserModel> optionalUserModel = userService.findById(id);
//        if (!optionalUserModel.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
//        }
//        var userModel = new UserModel();
//        BeanUtils.copyProperties(userDto, userModel);
//        userModel.setId(optionalUserModel.get().getId());
//        userModel.setRegistrationDate(optionalUserModel.get().getRegistrationDate());
//        userService.save(userModel);
//        return ResponseEntity.status(HttpStatus.OK).body(userModel);
//    }
}