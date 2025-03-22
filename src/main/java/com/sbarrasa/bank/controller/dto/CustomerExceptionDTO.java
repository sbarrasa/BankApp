package com.sbarrasa.bank.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sbarrasa.bank.service.exceptions.CustomerException;
import com.sbarrasa.bank.service.exceptions.CustomerProductException;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerExceptionDTO {
  private final String message;
  private final Integer customerId;
  private final ProductDTO product;

  public CustomerExceptionDTO(CustomerProductException ex) {
    this(ex.getMessage(), ex.getCustomerId(), ex.getProduct());
  }

  public CustomerExceptionDTO(CustomerException ex) {
    this(ex.getMessage(), ex.getCustomerId());
  }

  public CustomerExceptionDTO(String message, Integer customerId, ProductDTO product) {
    this.message = message;
    this.customerId = customerId;
    this.product = product;
  }

  public CustomerExceptionDTO(String message, Integer customerId) {
    this(message, customerId, null);
  }
}
