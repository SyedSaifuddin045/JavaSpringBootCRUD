package com.syedsaifuddin045.tasks.controllers;

import com.syedsaifuddin045.tasks.domain.dto.UserDTO;
import com.syedsaifuddin045.tasks.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // POST /api/users/register
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO registeredUser = userService.register(userDTO);
        return ResponseEntity.ok(registeredUser);
    }

    // POST /api/users/login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        String token = userService.login(userDTO);
        return ResponseEntity.ok(token);
    }

    // GET /api/users/me
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }
}
