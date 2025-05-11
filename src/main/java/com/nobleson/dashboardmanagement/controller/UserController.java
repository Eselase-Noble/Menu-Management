package com.nobleson.dashboardmanagement.controller;

import com.nobleson.dashboardmanagement.dto.UserDTO;
import com.nobleson.dashboardmanagement.serviceInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dashboard/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get-user")
    public ResponseEntity<UserDTO> getUserById(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.addUser(userDTO),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
     return new ResponseEntity<>(userService.updateUser(userDTO),HttpStatus.OK);
    }

    @PutMapping("/delete-user")
    public ResponseEntity<String> deleteUserById(@RequestParam("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted",HttpStatus.OK);
    }
}
