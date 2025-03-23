package com.sbarrasa.bank.service.exceptions;

import com.sbarrasa.bank.controller.dto.CustomerDTO;
import lombok.Getter;

@Getter
public abstract class CustomerException extends RuntimeException {
  private final CustomerDTO customer;
  public CustomerException(CustomerDTO customer) {
    this.customer = customer;
  }


}
