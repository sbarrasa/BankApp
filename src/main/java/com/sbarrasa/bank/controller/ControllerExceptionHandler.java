package com.sbarrasa.bank.controller;

import com.sbarrasa.bank.controller.dto.CustomerExceptionDTO;
import com.sbarrasa.bank.service.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
  @ExceptionHandler(CustomerProductException.class)
  public ResponseEntity<CustomerExceptionDTO> handleProductException(CustomerProductException ex) {
    return new ResponseEntity<>(new CustomerExceptionDTO(ex),
      HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CustomerException.class)
  public ResponseEntity<CustomerExceptionDTO> handleExistingCustomer(CustomerException ex) {
    return new ResponseEntity<>(new CustomerExceptionDTO(ex),
      HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception ex) {
    ex.printStackTrace();
    return new ResponseEntity<>("Error: %s".formatted(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
