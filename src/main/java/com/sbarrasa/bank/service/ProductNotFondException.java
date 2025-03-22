package com.sbarrasa.bank.service;

import com.sbarrasa.bank.controller.dto.ProductDTO;

public class ProductNotFondException extends CustomerProductException {

  public ProductNotFondException(Integer id, ProductDTO product) {
    super(id, product);
  }

  @Override
  protected String getErrorMessage() {
    return "Prducto %s no encontrado en cliente %d";
  }
}
