package com.sbarrasa.bank.service.exceptions;

import com.sbarrasa.bank.controller.dto.ProductDTO;

public class DuplicatedProductException extends CustomerProductException {


  public DuplicatedProductException(Integer customerId, ProductDTO product) {
    super(customerId, product);
  }

  @Override
  public String getMessage() {
    return "Producto duplicado";
  }

}
