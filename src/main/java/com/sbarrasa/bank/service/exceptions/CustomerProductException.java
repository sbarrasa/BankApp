package com.sbarrasa.bank.service.exceptions;

import com.sbarrasa.bank.controller.dto.ProductDTO;
import lombok.Getter;

@Getter
public abstract class CustomerProductException extends CustomerException {
  private final ProductDTO product;


  public CustomerProductException(Integer customerId, ProductDTO product) {
    super(customerId);
    this.product = product;
  }
}
