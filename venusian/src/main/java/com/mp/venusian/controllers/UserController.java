package com.mp.venusian.controllers;

import com.mp.venusian.dtos.UserDto;
import com.mp.venusian.models.UserModel;
import com.mp.venusian.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
@EnableJpaRepositories("com.mp.venusian.repositories.UserRepository")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto){
        if(userService.existsByEmail(userDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: User with this email already exists.");
        }
        if(userService.existsByPhone(userDto.getPhone())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: User with this phone already exists.");
        }
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable(value = "id") UUID id){
        Optional<UserModel> optionalUserModel = userService.findById(id);
        if (!optionalUserModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalUserModel.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id){
        Optional<UserModel> optionalUserModel = userService.findById(id);
        if (!optionalUserModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userService.delete(optionalUserModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid UserDto userDto){
        Optional<UserModel> optionalUserModel = userService.findById(id);
        if (!optionalUserModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setId(optionalUserModel.get().getId());
        userModel.setRegistrationDate(optionalUserModel.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
    }
}