package com.sbarrasa.bank.service;

import com.sbarrasa.bank.product.Product;

public class ProductNotFondException extends CustomerProductException {

  public ProductNotFondException(Integer id, Product product) {
    super(id, product);
  }

  @Override
  protected String getErrorMessage() {
    return "Prducto %s no encontrado en cliente %d";
  }
}
