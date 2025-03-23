package com.sbarrasa.util.validator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Supplier;

public class Validator {
  public static final String NOT_NULL = "no puede ser nulo";
  public static final String INVALID_VALUE  = "valor inválido";

  private final List<ValidationRule> validations = new ArrayList<>();

  public Validator addCondition(Supplier<Boolean> validation, String fieldName) {
    addCondition(validation, fieldName, INVALID_VALUE);
    return this;
  }

  public Validator addCondition(Supplier<Boolean> validation, String fieldName, String errorMessage) {
    validations.add(new ValidationRule(validation, fieldName, errorMessage));
    return this;
  }

  public Validator addNonNull(Supplier<?> supplier, String fieldName) {
    addCondition(() -> supplier.get() != null, fieldName, NOT_NULL);
    return this;
  }
  public void validate() {
    var errors = new LinkedHashMap<String, String>();

    for (ValidationRule rule : validations) {
      try {
        if (!rule.condition().get()) {
          errors.put(rule.fieldName(), rule.errorMessage());
        }
      }catch(Exception e){
        //Las excepciones en las validaciones no se guardan
      }
    }

    if(!errors.isEmpty())
      throw new ValidationException(errors);
  }

  private record ValidationRule(Supplier<Boolean> condition, String fieldName, String errorMessage) {}
}