package com.mp.venusian.controllers;

import com.mp.venusian.models.User;
import com.mp.venusian.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    @Autowired
    final UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable(value = "id") UUID id){
        //todo pegar user do header
        Optional<User> optionalUserModel = userService.findById(id);
        if (!optionalUserModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalUserModel.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id){
        Optional<User> optionalUser = userService.findById(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userService.delete(optionalUser.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
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
