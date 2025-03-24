package com.sbarrasa.bank.service.exceptions;

import lombok.Getter;

@Getter
public class ProductFactoryException extends IllegalArgumentException {
  private final String productType;


  public ProductFactoryException(String productType, String message) {
    super(message);
    this.productType = productType;
  }

  public ProductFactoryException(String message) {
    super(message);
    this.productType = null;
  }
}
