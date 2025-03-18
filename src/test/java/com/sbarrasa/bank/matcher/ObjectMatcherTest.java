package com.sbarrasa.bank.matcher;

import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ObjectMatcherTest {

  record Demo(Integer key, String color, String tamano){}

  Demo object1 = new Demo(1, "ROJO", "GRANDE");
  Demo object2 = new Demo(2, "VERDE", "CHICO");
  Demo object3 = new Demo(3, "ROJO", "MEDIANO");

  List<Demo> list = List.of(object1, object2, object3);


  @Test
  void allMatch() {
   var matcher = new ObjectMatcher<>(Demo::key, Demo::color);

   var sample = new Demo(1, "ROJO", null);

   assertTrue(matcher.match(object1, sample, Match.ALL));
   assertFalse(matcher.match(object2, sample, Match.ALL));
   assertFalse(matcher.match(object3, sample, Match.ALL));

  }


  @Test
  void anyMatch() {
    var matcher = new ObjectMatcher<>(Demo::key, Demo::color);

    var sample = new Demo(1, "ROJO", null);

    assertTrue(matcher.match(object1, sample, Match.ANY));
    assertFalse(matcher.match(object2, sample, Match.ANY));
    assertTrue(matcher.match(object3, sample, Match.ANY));

  }


  @Test
  void anyMatchWithNull() {
    var matcher = new ObjectMatcher<>(Demo::key, Demo::color);
    var sample = new Demo(1, null, null);

    assertTrue(matcher.match(object1, sample, Match.ANY));
    assertTrue(matcher.match(object1, sample, Match.ALL));
    assertFalse(matcher.match(object2, sample, Match.ANY));
    assertFalse(matcher.match(object2, sample, Match.ALL));

  }

  @Test
  void someAttributes() {
    var matcher = new ObjectMatcher<>(Demo::color);

    var sample = new Demo(null, "ROJO", null);

    assertTrue(matcher.match(object1, sample, Match.ALL));
    assertFalse(matcher.match(object2, sample, Match.ALL));
    assertTrue(matcher.match(object3, sample, Match.ALL));

  }

  @Test
  void exceptMatch() {
    var matcher = new ObjectMatcher<>(Demo::key, Demo::color);

    var sample = new Demo(1, "ROJO", null);

    assertFalse(matcher.match(object1, sample, Match.EXCEPT));
    assertTrue(matcher.match(object2, sample, Match.EXCEPT));
    assertFalse(matcher.match(object3, sample, Match.EXCEPT));
  }

  @Test
  void filterGetAll(){
    var matcher = new ObjectMatcher<>(Demo::key, Demo::color);

    var filtered = matcher.filter(list, object2, Match.ALL);

    assertEquals(1, filtered.size());

  }

  @Test
  void filterGetAllSomeAttributes(){
    var matcher = new ObjectMatcher<>(Demo::color);
    var sample = new Demo(null, "ROJO", null);

    var filtered = matcher.filter(list, sample, Match.ALL);
    assertEquals(2, filtered.size());

  }

  @Test
  void filterGetAny(){
    var matcher = new ObjectMatcher<>(Demo::key, Demo::color);
    var sample = new Demo(1, "ROJO", null);

    var filtered = matcher.filter(list, sample, Match.ANY);

    assertEquals(2, filtered.size());
  }

  @Test
  void filterExcept(){
    var matcher = new ObjectMatcher<>(Demo::key, Demo::color, Demo::tamano);
    var sample = new Demo(null, "VERDE", null);

    var filtered = matcher.filter(list, sample, Match.EXCEPT);

    assertEquals(2, filtered.size());
    assertEquals("ROJO", filtered.get(0).color);

  }


}