package com.keerthan.brainRot.controller;

import com.keerthan.brainRot.dto.user.UserRequestDTO;
import com.keerthan.brainRot.dto.user.UserResponseDTO;
import com.keerthan.brainRot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody UserRequestDTO userDTO) {
        userService.addUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("user registered successfully");
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>>  getUsers(){
        List<UserResponseDTO> users_list = userService.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users_list);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        String result = userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
