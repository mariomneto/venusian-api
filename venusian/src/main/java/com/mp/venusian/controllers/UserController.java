package com.mp.venusian.controllers;

import com.mp.venusian.dtos.UserDto;
import com.mp.venusian.enums.RegistrationType;
import com.mp.venusian.models.UserModel;
import com.mp.venusian.services.UserService;
import com.mp.venusian.util.Date;
import com.mp.venusian.util.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto) {
        if(userService.existsBy("email", userDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email already exists.");
        }
        if(userService.existsBy("phone", userDto.getPhone())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this phone already exists.");
        }
        if(userDto.getRegistrationType() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User needs a registration type.");
        }
        if(userDto.getRegistrationType() == RegistrationType.EMAIL && userDto.getEmail() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User needs an email.");
        }
        if(userDto.getRegistrationType() == RegistrationType.PHONE && userDto.getPhone() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User needs a phone.");
        }
        if(userDto.getEmail() != null && !Test.testEmail(userDto.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is invalid");
        }
        if(userDto.getPhone() != null && !Test.testPhone(userDto.getPhone())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Phone is invalid");
        }
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setRegistrationDate(Date.now());
        try {
            UserModel createdModel = userService.save(userModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdModel);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable(value = "id") String id){
        Optional<UserModel> optionalUserModel = userService.findById(id);
        if (!optionalUserModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalUserModel.get());
    }

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