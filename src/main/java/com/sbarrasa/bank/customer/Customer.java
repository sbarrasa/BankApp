package com.sbarrasa.bank.customer;

import com.sbarrasa.bank.util.matcher.MatchType;

public interface Customer {
  Integer getId();
  String getFirstName();
  String getLastName();
  Gender getGender();
  Address getAddress();
  String getEmail();
  String getPhoneNumber();

  default boolean match(Customer customerSample, MatchType matchType) {
    return new CustomerMatcher().match(this, customerSample, matchType);
  }
}
