package com.sbarrasa.bank.customer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@NoArgsConstructor
public final class CustomerDTO implements Customer {
  private Integer id;
  private String firstName;
  private String lastName;
  private Gender gender;
  private Address address;
  private String email;
  private String phoneNumber;


  public CustomerDTO(Customer customer) {
    this.setId(customer.getId());
    this.setFirstName(customer.getFirstName());
    this.setLastName(customer.getLastName());
    this.setGender(customer.getGender());
    this.setEmail(customer.getEmail());
    this.setAddress(customer.getAddress());
    this.setPhoneNumber(customer.getPhoneNumber());
  }


}
