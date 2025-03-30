package com.sbarrasa.bank.controller.dto;

import com.sbarrasa.bank.model.customer.Address;
import com.sbarrasa.bank.model.customer.Gender;

import java.time.LocalDateTime;

public interface Customer {
  Integer getId();

  String getFirstName();

  String getLastName();

  Gender getGender();

  Address getAddress();

  String getEmail();

  String getPhoneNumber();

  LocalDateTime getLastUpdate();
}
