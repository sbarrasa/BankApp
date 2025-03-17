package com.sbarrasa.bank.controller;

import com.sbarrasa.bank.service.CustomerNotFoundException;
import com.sbarrasa.bank.service.DuplicatedProductException;
import com.sbarrasa.bank.service.DuplicatedCustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
  @ExceptionHandler(DuplicatedProductException.class)
  public ResponseEntity<String> handleDuplicatedProduct(DuplicatedProductException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DuplicatedCustomerException.class)
  public ResponseEntity<String> handleExistingCustomer(DuplicatedCustomerException ex) {
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
