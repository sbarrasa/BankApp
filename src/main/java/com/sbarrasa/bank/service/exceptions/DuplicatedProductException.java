package com.sbarrasa.bank.service.exceptions;

import com.sbarrasa.bank.controller.dto.CustomerDTO;
import com.sbarrasa.bank.controller.dto.ProductDTO;

public class DuplicatedProductException extends CustomerProductException {

  public DuplicatedProductException(CustomerDTO customer, ProductDTO product) {
    super(customer, product);
  }

  @Override
  public String getMessage() {
    return "producto existente";
  }

}
