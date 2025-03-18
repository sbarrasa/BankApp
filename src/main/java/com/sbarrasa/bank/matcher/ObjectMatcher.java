package com.sbarrasa.bank.matcher;

import java.util.*;

public class ObjectMatcher<T> {

  private final Set<Getter<T>> getters;

  @SafeVarargs
  public ObjectMatcher(Getter<T>... getters) {
    this.getters = new HashSet<>(Arrays.asList(getters));
  }


  public boolean match(T object, T sampleObject, Match matchType) {
    for (Getter<T> getter : getters) {
      boolean attributeMatch;

      var sampleValue = getter.apply(sampleObject);

      if(sampleValue != null) {
        var objectValue = getter.apply(object);
        attributeMatch = sampleValue.equals(objectValue);

        if (attributeMatch && matchType == Match.ANY) return true;
        if (!attributeMatch && matchType == Match.ALL) return false;
      }
    }

    return matchType == Match.ALL;
  }

}