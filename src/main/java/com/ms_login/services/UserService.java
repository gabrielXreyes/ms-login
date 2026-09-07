package com.ms_login.services;

import com.ms_login.dto.UserCreateRequest;
import com.ms_login.dto.UserRequest;
import com.ms_login.entity.User;


public interface UserService {

    public void getUserByEmail(UserRequest userRequest);
    public void saveUser(UserCreateRequest userCreateRequest);

}
