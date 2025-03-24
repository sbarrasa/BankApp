package com.sbarrasa.util.validator;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;

public class Validator {
  public static void validate(Object object) {
    var validator = Validation.buildDefaultValidatorFactory().getValidator();
    var violations = validator.validate(object);
    if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
  }

}