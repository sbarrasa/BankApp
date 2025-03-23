package com.sbarrasa.bank.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sbarrasa.bank.service.exceptions.CustomerException;
import com.sbarrasa.bank.service.exceptions.CustomerProductException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CustomerExceptionDTO(String message, CustomerDTO customer, ProductDTO product){

  public CustomerExceptionDTO(CustomerException ex) {
    this(ex.getMessage(), ex.getCustomer(),  null);
  }

  public CustomerExceptionDTO(CustomerProductException ex) {
    this(ex.getMessage(), ex.getCustomer(),  ex.getProduct());
  }

}
