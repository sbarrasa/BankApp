package com.sbarrasa.util.id;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumDesc<E extends Enum<E> & Desc>{
  private final Class<E> enumType;

  public EnumDesc(Class<E> enumType) {
    this.enumType = enumType;
  }

  public Set<IdDesc<E>> asSet(){
    return Arrays.stream(enumType.getEnumConstants())
      .map(EnumDescAdapter::new)
      .collect(Collectors.toSet());
  }

}

