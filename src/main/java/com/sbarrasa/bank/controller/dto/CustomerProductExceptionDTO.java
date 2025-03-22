package com.sbarrasa.bank.controller.dto;

import com.sbarrasa.bank.service.exceptions.CustomerProductException;
import lombok.Getter;

@Getter

public class CustomerProductExceptionDTO extends CustomerExceptionDTO {
  private final ProductDTO product;

  public CustomerProductExceptionDTO(CustomerProductException ex){
    this(ex.getMessage(), ex.getCustomerId(), ex.getProduct());
  }
  public CustomerProductExceptionDTO(String message, Integer customerId, ProductDTO product) {
    super(message, customerId);
    this.product = product;
  }
}
