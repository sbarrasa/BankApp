package com.sbarrasa.bank.controller.dto;

import com.sbarrasa.bank.customer.Address;
import com.sbarrasa.bank.customer.Customer;
import com.sbarrasa.bank.customer.Gender;
import com.sbarrasa.bank.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;


@Data
@Accessors(chain = true)
@NoArgsConstructor
public class CustomerDTO implements Customer {
  private Integer id;
  private String firstName;
  private String lastName;
  private Gender gender;
  private Address address;
  private String email;
  private String phoneNumber;
  private Set<Product> products;


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
