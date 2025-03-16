package com.sbarrasa.bank.service;

public abstract class CustomerException extends RuntimeException{
  private final Integer id;
  protected abstract String getErrorMessage();

  public CustomerException(Integer id) {
    this.id = id;
  }

  @Override
  public String getMessage(){
    return getErrorMessage().formatted(id);
  }
}
