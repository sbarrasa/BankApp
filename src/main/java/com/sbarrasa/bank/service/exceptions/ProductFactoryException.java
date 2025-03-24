package com.sbarrasa.bank.service.exceptions;

import lombok.Getter;

@Getter
public class ProductFactoryException extends IllegalArgumentException {
  public ProductFactoryException(String message) {
    super(message);
  }


}
