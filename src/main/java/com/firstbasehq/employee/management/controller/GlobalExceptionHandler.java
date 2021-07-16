package com.firstbasehq.employee.management.controller;

import com.firstbasehq.employee.management.exception.EmployeeManagementException;
import com.firstbasehq.employee.management.integrations.randomuser.RandomUserException;
import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Art Yesipovich 7/15/21
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(EmployeeManagementException.buildException(ex.getMessage()),
                                    HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RandomUserException.class)
    public ResponseEntity randomUserIntegrationException(RandomUserException ex) {
        return new ResponseEntity<>(EmployeeManagementException.buildException(ex.getMessage()),
                                    HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity invalidArgumentException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return new ResponseEntity<>(EmployeeManagementException.buildException(message),
                                    HttpStatus.BAD_REQUEST);
    }
}
