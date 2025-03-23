package com.sbarrasa.bank.service.exceptions;


import com.sbarrasa.bank.controller.dto.CustomerDTO;

public class DuplicatedCustomerException extends CustomerException {


  public DuplicatedCustomerException(CustomerDTO customer) {
    super(customer);
  }

  @Override
  public String getMessage() {
    return "Cliente duplicado";
  }
}
