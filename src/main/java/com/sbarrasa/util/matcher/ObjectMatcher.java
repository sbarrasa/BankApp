package com.sbarrasa.util.matcher;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjectMatcher<T> {

  private final Set<GetFunction<T>> getters;

  @SafeVarargs
  public ObjectMatcher(GetFunction<T>... getters) {
    this.getters = new HashSet<>(Arrays.asList(getters));
  }


  public boolean match(T object, T sampleObject, MatchType matchType) {
    var matchableGetters = getMatcheableGetters(sampleObject);

    if(matchableGetters.isEmpty()) return false;

    for (GetFunction<T> getter : matchableGetters) {
      var sampleValue = getter.apply(sampleObject);
      var objectValue = getter.apply(object);
      boolean valueMatch = sampleValue.equals(objectValue);

      if (valueMatch && matchType == MatchType.ANY) return true;
      if (!valueMatch && matchType == MatchType.ALL) return false;
    }

    return matchType == MatchType.ALL;
  }

  public Set<GetFunction<T>> getMatcheableGetters(T sampleObject) {
    return getters.stream()
      .filter(getter -> getter.apply(sampleObject) != null)
      .collect(Collectors.toSet());
  }
}