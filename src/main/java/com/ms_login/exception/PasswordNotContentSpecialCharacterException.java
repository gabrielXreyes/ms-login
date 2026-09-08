package com.ms_login.exception;

public class PasswordNotContentSpecialCharacterException extends RuntimeException {
    public PasswordNotContentSpecialCharacterException(String message) {
        super(message);
    }
}
