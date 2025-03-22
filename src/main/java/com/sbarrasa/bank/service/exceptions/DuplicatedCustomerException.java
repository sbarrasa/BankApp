package com.sbarrasa.bank.service.exceptions;


public class DuplicatedCustomerException extends CustomerException {

  public DuplicatedCustomerException(Integer customerId) {
    super(customerId);
  }

  @Override
  public String getMessage() {
    return "Cliente duplicado";
  }
}
