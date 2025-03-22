package com.sbarrasa.bank.controller.dto;

import com.sbarrasa.bank.service.exceptions.CustomerException;
import lombok.Getter;

@Getter
public class CustomerExceptionDTO {
  private final String message;
  private final Integer customerId;

  public CustomerExceptionDTO(CustomerException ex) {
    this(ex.getMessage(), ex.getCustomerId());
  }

  public CustomerExceptionDTO(String message, Integer customerId) {
    this.message = message;
    this.customerId = customerId;
  }
}
