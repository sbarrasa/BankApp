package com.sbarrasa.util.validator;

import lombok.Getter;

import java.util.Map;

@Getter
public class ValidationException extends RuntimeException {
  private final Map<String, String> errors;

  public ValidationException(Map<String, String> errors) {
    super("Error "+ errors);
    this.errors = errors;
  }
}
