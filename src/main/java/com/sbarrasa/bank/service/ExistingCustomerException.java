package com.sbarrasa.bank.service;

public class ExistingCustomerException extends CustomerException {

  public ExistingCustomerException(Integer id) {
    super(id);
  }

  @Override
  protected String getErrorMessage() {
    return "Duplicated customer %d";
  }
}
