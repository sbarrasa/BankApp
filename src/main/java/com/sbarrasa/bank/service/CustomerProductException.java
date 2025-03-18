package com.sbarrasa.bank.service;

import com.sbarrasa.bank.product.Product;
import lombok.Getter;

@Getter
public abstract class CustomerProductException extends CustomerException {
  private final Product product;

  public CustomerProductException(Integer id, Product product) {
    super(id);
    this.product = product;
  }

  @Override
  public String getMessage(){
    return getErrorMessage().formatted(product, getId());
  }

}
