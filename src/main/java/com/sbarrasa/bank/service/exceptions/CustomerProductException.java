package com.sbarrasa.bank.service.exceptions;

import com.sbarrasa.bank.controller.dto.CustomerDTO;
import com.sbarrasa.bank.controller.dto.ProductDTO;
import lombok.Getter;

@Getter
public class CustomerProductException extends CustomerException {
  private final ProductDTO product;
  public static final String NOT_FOUND = "producto no encontrado en cliente";
  public static final String DUPLICATED = "producto existente en cliente";

  public CustomerProductException(CustomerDTO customer, ProductDTO product, String message) {
    super(customer, message);
    this.product = product;
  }
}
