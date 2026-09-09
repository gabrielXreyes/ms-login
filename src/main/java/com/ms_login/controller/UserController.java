package com.ms_login.controller;

import com.ms_login.dto.UserCreateRequest;

import com.ms_login.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private  final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody UserCreateRequest userCreateRequestRequest) {
        return userService.saveUser(userCreateRequestRequest);
    }
}
