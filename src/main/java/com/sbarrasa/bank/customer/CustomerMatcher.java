package com.sbarrasa.bank.customer;

import com.sbarrasa.util.matcher.ObjectMatcher;

public class CustomerMatcher extends ObjectMatcher<Customer> {
  public CustomerMatcher() {
    super(
      Customer::getId,
      Customer::getFirstName,
      Customer::getLastName,
      Customer::getGender,
      Customer::getEmail,
      Customer::getPhoneNumber

    );
  }
}
