package com.sbarrasa.bank.controller;

import com.sbarrasa.bank.service.CustomerNotFoundException;
import com.sbarrasa.bank.service.ExistingCustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
  @ExceptionHandler(ExistingCustomerException.class)
  public ResponseEntity<String> handleExistingCustomer(ExistingCustomerException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CustomerNotFoundException.class)
  public ResponseEntity<String> handleCustomerNotFound(CustomerNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception ex) {
    ex.printStackTrace();
    return new ResponseEntity<>("Error: %s".formatted(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
