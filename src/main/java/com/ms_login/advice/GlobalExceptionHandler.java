package com.ms_login.advice;

import com.ms_login.constant.Constants;
import com.ms_login.errors.ApiErrors;
import com.ms_login.exception.*;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected @Nullable ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> details = new ArrayList<String>();
        details.add("The user exist");
        ApiErrors errors =new ApiErrors(ex.getMessage(),details,status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> detalles = new ArrayList<String>();
        detalles.add("MediaType no soportado");
        detalles.add(ex.getMessage());
        ApiErrors errores = new ApiErrors(ex.getMessage(), detalles, status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errores);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> detalles = new ArrayList<String>();
        detalles.add("Variable de URL no encontrada");
        ApiErrors errores = new ApiErrors(ex.getMessage(), detalles, status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errores);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> detalles = new ArrayList<String>();
        detalles.add("Parámetro de peticion no encontrado");
        ApiErrors errores = new ApiErrors(ex.getMessage(), detalles, status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errores);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        final List<String> errors = new ArrayList<String>();


        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        ApiErrors errores = new ApiErrors("Datos inválidos", errors, HttpStatusCode.valueOf(422), LocalDateTime.now());
        return ResponseEntity.status(HttpStatusCode.valueOf(422)).body(errores);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> detalles = new ArrayList<String>();
        detalles.add("Formatos no coinciden");
        ApiErrors errores = new ApiErrors(ex.getMessage(), detalles, status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errores);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> detalles = new ArrayList<String>();
        detalles.add("RequestBody is not readable");
        ApiErrors errores = new ApiErrors(ex.getMessage(), detalles, status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errores);
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<Object> UserExistException(RuntimeException ex){
        List<String> detalles = new ArrayList<String>();
        detalles.add(Constants.userExit);
        ApiErrors errores = new ApiErrors(ex.getMessage(), detalles, HttpStatusCode.valueOf(404), LocalDateTime.now());
        return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(errores);
    }

    @ExceptionHandler(PasswordShortException.class)
    public ResponseEntity<Object> PasswordSortException(@NonNull RuntimeException ex){
        List<String> detalles = new ArrayList<>();
        detalles.add(Constants.passwordSort);
        ApiErrors errors = new ApiErrors(ex.getMessage(), detalles, HttpStatusCode.valueOf(400), LocalDateTime.now());
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(errors);
    }

    @ExceptionHandler(PasswordNotUppercase.class)
    public ResponseEntity<Object> PasswordNotUppercaseException(@NonNull RuntimeException ex){
        List<String> detalles = new ArrayList<>();
        detalles.add(Constants.passwordDontContentUpper);
        ApiErrors apiErrors= new ApiErrors(ex.getMessage(), detalles, HttpStatusCode.valueOf(400), LocalDateTime.now());
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(apiErrors);
    }

    @ExceptionHandler(PasswordNotContentSpecialCharacterException.class)
    public ResponseEntity<Object> PasswordNotContentSpecialCharacterException(@NonNull RuntimeException ex){
        List<String> detalles = new ArrayList<>();
        detalles.add(Constants.passwordDontContentSpecialCharacter);
        ApiErrors apiErrors = new ApiErrors(ex.getMessage(), detalles, HttpStatusCode.valueOf(400), LocalDateTime.now());
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(apiErrors);
    }
    @ExceptionHandler(UsernameShortException.class)
    public ResponseEntity<Object> UsernameShortException(@NonNull RuntimeException ex){
        List<String> detalles = new ArrayList<>();
        detalles.add(Constants.usernameTooShort);
        ApiErrors apiErrors = new ApiErrors(ex.getMessage(), detalles, HttpStatusCode.valueOf(400), LocalDateTime.now());
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(apiErrors);
    }

    @ExceptionHandler(DomainNoExistException.class)
    public ResponseEntity<Object> DomainNoExistException(@NonNull RuntimeException ex){
        List<String> detalles = new ArrayList<>();
        detalles.add(Constants.userExit);
        ApiErrors apiErrors = new ApiErrors(ex.getMessage(), detalles, HttpStatusCode.valueOf(404), LocalDateTime.now());
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(apiErrors);
    }
}
