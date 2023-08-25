package com.mp.venusian.controllers;

import com.mp.venusian.models.User;
import com.mp.venusian.services.UserService;
import com.mp.venusian.util.HeaderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    final UserService userService;
    @Autowired
    final HeaderUtil headerUtil;
    @GetMapping("/me")
    public ResponseEntity<Object> getMe(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        var userId = headerUtil.getUserIdFromAuthHeader(authHeader);
        if(userId.isPresent()) {
            Optional<User> user = userService.findById(userId.get());
            if(user.isPresent()){
                return ResponseEntity.status((HttpStatus.OK)).body(user);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }

    @DeleteMapping("/me")
    public ResponseEntity<Object> deleteMe(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        var userId = headerUtil.getUserIdFromAuthHeader(authHeader);
        if(userId.isPresent()) {
            Optional<User> user = userService.findById(userId.get());
            if(user.isPresent()){
                userService.deleteById(userId.get());
                return ResponseEntity.status((HttpStatus.OK)).body("User deleted successfully.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }

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
