package com.sbarrasa.bank.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sbarrasa.bank.controller.dto.CustomerExceptionDTO;
import com.sbarrasa.bank.service.exceptions.*;
import com.sbarrasa.util.validator.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class ControllerExceptionHandler {
  @ExceptionHandler(CustomerProductException.class)
  public ResponseEntity<CustomerExceptionDTO> handle(CustomerProductException ex) {
    return new ResponseEntity<>(new CustomerExceptionDTO(ex),
      HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CustomerException.class)
  public ResponseEntity<CustomerExceptionDTO> handle(CustomerException ex) {
    return new ResponseEntity<>(new CustomerExceptionDTO(ex),
      HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<Map<String, String>> handle(ValidationException ex) {
    return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidFormatException.class)
  public ResponseEntity<Map<String, String>> handle(InvalidFormatException ex) {
    Class<?> targetType = ex.getTargetType();
    String fieldName = ex.getPath().get(0).getFieldName();
    String message;

    if (targetType.isEnum()) {
      var possibleValues = Arrays.stream(((Class<? extends Enum>) targetType).getEnumConstants()).toList();
      message = "valores posibles %s".formatted(possibleValues);
    }else{
      message = "No es un %s valido".formatted(targetType.getSimpleName());
    }

    return new ResponseEntity<>(Map.of(fieldName, message), HttpStatus.BAD_REQUEST);
  }
}
