package com.sbarrasa.bank.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sbarrasa.bank.model.customer.Customer;
import com.sbarrasa.bank.service.exceptions.CustomerException;
import com.sbarrasa.bank.service.exceptions.CustomerProductException;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public final class CustomerExceptionDTO {
  private final Customer customer;
  private final ProductDTO product;
  private final String message;

  public CustomerExceptionDTO(String message, Customer customer, ProductDTO product) {
    this.message = message;
    this.customer = createSimpleCustomer(customer);
    this.product = product;
  }

  private CustomerDTO createSimpleCustomer(Customer customer) {
    return new CustomerDTO()
      .setId(customer.getId())
      .setFirstName(customer.getFirstName())
      .setLastName(customer.getLastName());
  }

  public CustomerExceptionDTO(CustomerException ex) {
    this(ex.getMessage(), ex.getCustomer(), null);
  }

  public CustomerExceptionDTO(CustomerProductException ex) {
    this(ex.getMessage(), ex.getCustomer(), ex.getProduct());
  }


}
