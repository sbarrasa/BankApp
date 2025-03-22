package com.sbarrasa.bank.service.exceptions;

import com.sbarrasa.bank.controller.dto.ProductDTO;

public class ProductNotFondException extends CustomerProductException {


  public ProductNotFondException(Integer customerId, ProductDTO product) {
    super(customerId, product);
  }

  @Override
  public String getMessage() {
    return "Prducto no encontrado";
  }
}
