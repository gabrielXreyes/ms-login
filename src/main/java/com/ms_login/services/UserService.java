package com.ms_login.services;

import com.ms_login.dto.LoginRequest;
import com.ms_login.dto.TokenResponse;
import com.ms_login.dto.UserCreateRequest;
import org.springframework.http.ResponseEntity;


public interface UserService {

    ResponseEntity<TokenResponse> saveUser(UserCreateRequest userCreateRequest);
    ResponseEntity<String> loginUser(LoginRequest loginRequest);



    //validations
    void validationPassword(UserCreateRequest userCreateRequest);
    void validationEmail(UserCreateRequest userCreateRequest);
    void validationUsername(UserCreateRequest userCreateRequest);
}
