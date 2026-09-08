package com.ms_login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.aspectj.bridge.Message;

public class UserCreateRequest {
    @NotBlank(message = "the username is required")
    private String userName;

    @NotBlank(message = "the email is required")
    @Email(message = "The email isn't in a valid format")
    private String email;

    @NotBlank(message = "the username is required")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {this.password = password;}
}
