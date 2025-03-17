package com.sbarrasa.bank.service;

public class CustomerNotFoundException extends CustomerException {

  public CustomerNotFoundException(Integer id) {
    super(id);
  }

  @Override
  protected String getErrorMessage() {
    return "Cliente %d no encontrado";
  }
}
