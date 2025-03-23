package com.sbarrasa.bank.service.exceptions;

import com.sbarrasa.bank.controller.dto.CustomerDTO;
import com.sbarrasa.bank.controller.dto.ProductDTO;
import lombok.Getter;

@Getter
public abstract class CustomerProductException extends CustomerException {
  private final ProductDTO product;


  public CustomerProductException(CustomerDTO customer, ProductDTO product) {
    super(customer);
    this.product = product;
  }
}
