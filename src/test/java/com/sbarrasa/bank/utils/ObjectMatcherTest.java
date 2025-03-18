package com.sbarrasa.bank.utils;

import com.sbarrasa.bank.utils.attribute.AttributesBuilder;
import com.sbarrasa.bank.utils.matcher.AttributePairInfo;
import com.sbarrasa.bank.utils.matcher.ObjectMatcher;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ObjectMatcherTest {
  LocalDate date1 = LocalDate.of(1974, 6, 7);
  LocalDate date2 = LocalDate.of(2025, 6, 7);
  LocalDate date3 = LocalDate.of(2025, 6, 1);

  private record TestInteger(Integer value){}

  @Test
  void match(){
   var matcher = new ObjectMatcher<LocalDate>("getMonth", "getDayOfMonth");

   assertTrue(matcher.allMatch(date1, date2));
   assertFalse(matcher.allMatch(date2, date3));
   assertTrue(matcher.anyMatch(date2, date3));

  }

  @Test
  void matchDiferentAttributes(){

    var matcher = new ObjectMatcher<>(AttributesBuilder.build(new AttributePairInfo("value", "getYear")));
    var date = LocalDate.of(1974, 6,7);
    var testInteger = new TestInteger(1974);

    assertTrue(matcher.allMatch(testInteger, date));

  }

}