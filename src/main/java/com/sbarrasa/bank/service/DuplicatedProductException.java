package com.sbarrasa.bank.service;

import com.sbarrasa.bank.controller.dto.ProductDTO;

public class DuplicatedProductException extends CustomerProductException {

  public DuplicatedProductException(Integer id, ProductDTO product) {
    super(id, product);
  }

  @Override
  protected String getErrorMessage() {
    return "Producto %s duplicado en cliente %d";
  }

}
