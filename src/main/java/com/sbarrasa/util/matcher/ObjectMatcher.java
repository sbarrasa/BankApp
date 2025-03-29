package com.sbarrasa.util.matcher;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ObjectMatcher<T> {

  private final Set<GetFuntion<T>> getters;

  @SafeVarargs
  public ObjectMatcher(GetFuntion<T>... getters) {
    this.getters = new HashSet<>(Arrays.asList(getters));
  }


  public boolean match(T object, T sampleObject, MatchType matchType) {
    if (!hasCriteria(sampleObject))
      return false;

    for (GetFuntion<T> getFuntion : getters) {

      boolean attributeMatch;

      var sampleValue = getFuntion.apply(sampleObject);

      if (sampleValue != null) {
        var objectValue = getFuntion.apply(object);
        attributeMatch = sampleValue.equals(objectValue);

        if (attributeMatch && matchType == MatchType.ANY) return true;
        if (!attributeMatch && matchType == MatchType.ALL) return false;
      }
    }

    return matchType == MatchType.ALL;
  }

  private boolean hasCriteria(T sampleObject) {
    return getters.stream()
      .anyMatch(getFuntion -> getFuntion.apply(sampleObject) != null);
  }

}