package com.sbarrasa.bank.service;

import com.sbarrasa.bank.product.Product;

public class DuplicatedProductException extends CustomerProductException {

  public DuplicatedProductException(Integer id, Product product) {
    super(id, product);
  }

  @Override
  protected String getErrorMessage() {
    return "Producto %s duplicado en cliente %d";
  }

}
