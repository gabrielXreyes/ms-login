package com.ms_login.controller;

import com.ms_login.dto.UserCreateRequest;
import com.ms_login.dto.UserRequest;
import com.ms_login.entity.User;
import com.ms_login.services.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private  final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<String> login(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<String>(userRequest.getEmail(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/create")
    public ResponseEntity<String> create(@RequestBody UserCreateRequest userCreateRequestRequest) {

        userService.saveUser(userCreateRequestRequest);
        return new ResponseEntity<String>(, HttpStatusCode.valueOf(200));
    }
}
