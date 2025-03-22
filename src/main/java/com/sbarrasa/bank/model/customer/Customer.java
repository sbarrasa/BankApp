package com.sbarrasa.bank.model.customer;

public interface Customer {
  Integer getId();
  String getFirstName();
  String getLastName();
  Gender getGender();
  Address getAddress();
  String getEmail();
  String getPhoneNumber();
}
