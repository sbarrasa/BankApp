package com.sbarrasa.bank.customer;

import com.sbarrasa.bank.util.matcher.MatchType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMatcherTest {
  Customer customer1 = new CustomerDTO()
    .setId(24)
    .setFirstName("Sebastian")
    .setLastName("Barrasa")
    .setEmail("sebastian.barrasa@yahoo.com")
    .setGender(Gender.M)
    .setPhoneNumber("+54 9 11 56575219");

  Customer customer2 = new CustomerDTO()
    .setId(38)
    .setFirstName("Esteban")
    .setLastName("Ortenzi")
    .setEmail("eortenzi@yahoo.com")
    .setGender(Gender.M);


  @Test
  void matchAny(){
    var matcher = new CustomerMatcher();

    var customerSample = new CustomerDTO()
      .setGender(Gender.M)
      .setFirstName("Sebastian");

      assertTrue(matcher.match(customer1, customerSample, MatchType.ALL));
      assertTrue(matcher.match(customer2, customerSample, MatchType.ANY));
      assertFalse(matcher.match(customer2, customerSample, MatchType.ALL));

  }

  @Test
  void filter(){
    var matcher = new CustomerMatcher();

    var customerSample = new CustomerDTO()
      .setFirstName("Sebastian")
      .setGender(Gender.M);

    var customerList = List.of(customer1, customer2);

    var filtered1 = customerList.stream()
        .filter(customer -> matcher.match(customer, customerSample, MatchType.ALL))
        .toList();

    var filtered2 = customerList.stream()
      .filter(customer -> matcher.match(customer, customerSample, MatchType.ANY))
      .toList();

    assertEquals(1, filtered1.size());
    assertEquals(2, filtered2.size());
    assertEquals("Barrasa", filtered1.get(0).getLastName());

  }


}