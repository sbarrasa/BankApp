package com.sbarrasa.bank.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sbarrasa.bank.controller.dto.CustomerExceptionDTO;
import com.sbarrasa.bank.service.exceptions.CustomerException;
import com.sbarrasa.bank.service.exceptions.CustomerProductException;
import com.sbarrasa.bank.service.exceptions.ProductFactoryException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

  @ExceptionHandler(InvalidFormatException.class)
  public ResponseEntity<Map<String, String>> handle(InvalidFormatException ex) {
    Class<?> targetType = ex.getTargetType();
    String fieldName = ex.getPath().get(0).getFieldName();
    String message;

    if (targetType.isEnum()) {
      var possibleValues = Arrays.stream(((Class<? extends Enum<?>>) targetType).getEnumConstants()).toList();
      message = "valores posibles %s".formatted(possibleValues);
    } else {
      message = "No es un %s valido".formatted(targetType.getSimpleName());
    }

    return new ResponseEntity<>(Map.of(fieldName, message), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Map<String, String>> handleValidation(ConstraintViolationException ex) {
    Map<String, String> errors = new HashMap<>();
    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      String field = violation.getPropertyPath().toString();
      errors.put(field, violation.getMessage());
    }
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ProductFactoryException.class)
  public ResponseEntity<Map<String, String>> handle(ProductFactoryException ex) {
    return new ResponseEntity<>(Map.of("productType", ex.getMessage()),
      HttpStatus.BAD_REQUEST);
  }
}
