package com.sbarrasa.util.validator;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;

public class Validator {
  public void validate(Object object) {
    try(var validatorFactory = Validation.buildDefaultValidatorFactory()){
      var validator = validatorFactory.getValidator();
      var violations = validator.validate(object);
      if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }
  }

}