package com.sbarrasa.bank.service.exceptions;

import com.sbarrasa.bank.controller.dto.CustomerDTO;
import lombok.Getter;

@Getter
public class CustomerException extends RuntimeException {
  public static final String NOT_FOUND = "cliente no encontrado";
  public static final String DUPLICATED = "cliente existente";

  private final CustomerDTO customer;
  public CustomerException(CustomerDTO customer, String message) {
    super(message);
    this.customer = customer;
  }


}
