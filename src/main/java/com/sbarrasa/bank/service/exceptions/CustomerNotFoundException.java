package com.sbarrasa.bank.service.exceptions;

public class CustomerNotFoundException extends CustomerException {
  public CustomerNotFoundException(Integer customerId) {
    super(customerId);
  }

  @Override
  public String getMessage() {
    return "Cliente no encontrado";
  }
}
