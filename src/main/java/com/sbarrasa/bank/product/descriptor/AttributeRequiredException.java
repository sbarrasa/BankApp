package com.sbarrasa.bank.product.descriptor;

import lombok.Getter;

import java.util.Set;

@Getter
public class AttributeRequiredException extends RuntimeException {
  private final Object object;
  private final Set<String> missingAttributes;

  public AttributeRequiredException(Object object, Set<String> missingAttributes) {
    this.object = object;
    this.missingAttributes = missingAttributes;
  }

  @Override
  public String getMessage() {
    return "%s requiere %s".formatted(object.toString(), missingAttributes);
  }

}
