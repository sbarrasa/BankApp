package com.sbarrasa.bank.service;

import com.sbarrasa.bank.product.Product;

public class DuplicatedProductException extends CustomerException {

  private final Product product;

  public DuplicatedProductException(Integer id, Product product) {
    super(id);
    this.product = product;
  }

  @Override
  protected String getErrorMessage() {
    return "Producto %s duplicado en el cliente %d";
  }

  @Override
  public String getMessage(){
    return getErrorMessage().formatted(product, getId());
  }
}
