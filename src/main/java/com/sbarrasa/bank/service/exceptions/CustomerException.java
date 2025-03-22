package com.sbarrasa.bank.service.exceptions;

import lombok.Getter;

@Getter
public abstract class CustomerException extends RuntimeException {
  private final Integer customerId;
  public CustomerException(Integer customerId) {
    this.customerId = customerId;
  }


}
