package com.sbarrasa.bank.service.exceptions;

import com.sbarrasa.bank.model.customer.Customer;
import lombok.Getter;

@Getter
public class CustomerException extends RuntimeException {
  public static final String NOT_FOUND = "cliente no encontrado";
  public static final String DUPLICATED = "cliente existente";

  private final Customer customer;

  public CustomerException(Customer customer, String message) {
    super(message);
    this.customer = customer;
  }


}
