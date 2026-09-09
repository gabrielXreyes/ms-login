package com.ms_login.services;

import com.ms_login.dto.UserCreateRequest;
import org.springframework.http.ResponseEntity;


public interface UserService {

    ResponseEntity<String> saveUser(UserCreateRequest userCreateRequest);
    void validationPassword(UserCreateRequest userCreateRequest);
    void validationEmail(UserCreateRequest userCreateRequest);
    void validationUsername(UserCreateRequest userCreateRequest);
}
