package com.sbarrasa.bank.util.matcher;

import java.util.*;

public class ObjectMatcher<T> {

  private final Set<Getter<T>> getters;

  @SafeVarargs
  public ObjectMatcher(Getter<T>... getters) {
    this.getters = new HashSet<>(Arrays.asList(getters));
  }

  public boolean matchValue(Object value, Object sampleValue) {
    return sampleValue == null
      || sampleValue.equals(value);
  }

  public boolean match(T object, T sampleObject, MatchType matchType) {
    for (Getter<T> getter : getters) {
      boolean attributeMatch;

      var sampleValue = getter.apply(sampleObject);

      if(sampleValue != null) {
        var objectValue = getter.apply(object);
        attributeMatch = sampleValue.equals(objectValue);

        if (attributeMatch && matchType == MatchType.ANY) return true;
        if (!attributeMatch && matchType == MatchType.ALL) return false;
      }
    }

    return matchType == MatchType.ALL;
  }

}