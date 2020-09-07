package com.dev.microservices.core.bank.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<String> exceptionClientAlreadyExistsHandler(ClientAlreadyExistsException ex) {
        return ResponseEntity.status(FORBIDDEN)
                .body(String.format("SSN already exists: %s", ex.getSsn()));
    }

    @ExceptionHandler(BankDoesNotExistException.class)
    public ResponseEntity<String> exceptionBankDoesNotExistHandler(BankDoesNotExistException ex) {
        return ResponseEntity.status(NOT_FOUND)
                .body(String.format("The specified client's bank doesn't exist: bank ID = %s", ex.getBankId()));
    }
}