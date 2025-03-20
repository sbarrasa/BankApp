package com.sbarrasa.bank.matcher;

import com.sbarrasa.util.matcher.MatchType;
import com.sbarrasa.util.matcher.ObjectMatcher;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ObjectMatcherTest {

  record Demo(Integer key, String color, String tamano){}

  Demo object1 = new Demo(1, "ROJO", "GRANDE");
  Demo object2 = new Demo(2, "VERDE", "CHICO");
  Demo object3 = new Demo(3, "ROJO", "MEDIANO");


  @Test
  void allMatch() {
   var matcher = new ObjectMatcher<>(Demo::key, Demo::color);

   var sample = new Demo(1, "ROJO", null);

   assertTrue(matcher.match(object1, sample, MatchType.ALL));
   assertFalse(matcher.match(object2, sample, MatchType.ALL));
   assertFalse(matcher.match(object3, sample, MatchType.ALL));

  }


  @Test
  void anyMatch() {
    var matcher = new ObjectMatcher<>(Demo::key, Demo::color);

    var sample = new Demo(1, "ROJO", null);

    assertTrue(matcher.match(object1, sample, MatchType.ANY));
    assertFalse(matcher.match(object2, sample, MatchType.ANY));
    assertTrue(matcher.match(object3, sample, MatchType.ANY));

  }


  @Test
  void anyMatchWithNull() {
    var matcher = new ObjectMatcher<>(Demo::key, Demo::color);
    var sample = new Demo(1, null, null);

    assertTrue(matcher.match(object1, sample, MatchType.ANY));
    assertTrue(matcher.match(object1, sample, MatchType.ALL));
    assertFalse(matcher.match(object2, sample, MatchType.ANY));
    assertFalse(matcher.match(object2, sample, MatchType.ALL));

  }

  @Test
  void someAttributes() {
    var matcher = new ObjectMatcher<>(Demo::color);

    var sample = new Demo(null, "ROJO", null);

    assertTrue(matcher.match(object1, sample, MatchType.ALL));
    assertFalse(matcher.match(object2, sample, MatchType.ALL));
    assertTrue(matcher.match(object3, sample, MatchType.ALL));

  }


}