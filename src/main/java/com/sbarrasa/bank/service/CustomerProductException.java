package com.sbarrasa.bank.service;

import com.sbarrasa.bank.controller.dto.ProductDTO;
import lombok.Getter;

@Getter
public abstract class CustomerProductException extends CustomerException {
  private final ProductDTO product;

  public CustomerProductException(Integer id, ProductDTO product) {
    super(id);
    this.product = product;
  }

  @Override
  public String getMessage(){
    return getErrorMessage().formatted(product, getId());
  }

}
