package com.ms_login.exception;

public class PasswordNotUppercase extends RuntimeException {
  public PasswordNotUppercase(String message) {
    super(message);
  }
}
