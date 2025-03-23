package com.sbarrasa.bank.service.exceptions;

import com.sbarrasa.bank.controller.dto.CustomerDTO;

public class CustomerNotFoundException extends CustomerException {

  public CustomerNotFoundException(CustomerDTO customer) {
    super(customer);
  }

  @Override
  public String getMessage() {
    return "Cliente no encontrado";
  }
}
