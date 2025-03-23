package com.sbarrasa.bank.service.exceptions;

import com.sbarrasa.bank.controller.dto.CustomerDTO;
import com.sbarrasa.bank.controller.dto.ProductDTO;

public class ProductNotFondException extends CustomerProductException {


  public ProductNotFondException(CustomerDTO customer, ProductDTO product) {
    super(customer, product);
  }

  @Override
  public String getMessage() {
    return "Prducto no encontrado";
  }
}
