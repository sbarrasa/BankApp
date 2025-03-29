package com.sbarrasa.bank.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sbarrasa.bank.model.customer.Address;
import com.sbarrasa.bank.model.customer.Customer;
import com.sbarrasa.bank.model.customer.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class CustomerDTO implements Customer {
  private Integer id;
  private String firstName;
  private String lastName;
  private Gender gender;
  private Address address;
  private String email;
  private String phoneNumber;
  private LocalDateTime lastUpdate;
}
