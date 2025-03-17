package com.sbarrasa.bank.service;

public class DuplicatedCustomerException extends CustomerException {

  public DuplicatedCustomerException(Integer id) {
    super(id);
  }

  @Override
  protected String getErrorMessage() {
    return "Cliente %d duplicado";
  }
}
