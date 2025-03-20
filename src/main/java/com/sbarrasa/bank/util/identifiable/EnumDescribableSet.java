package com.sbarrasa.bank.util.identifiable;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumDescribableSet<E extends Enum<E> & Describable>{
  private final Class<E> enumType;

  public EnumDescribableSet(Class<E> enumType) {
    this.enumType = enumType;
  }

  public Set<IdDescribable<E>> listAll(){
    return Arrays.stream(enumType.getEnumConstants())
      .map(EnumDescribableAdapter::new)
      .collect(Collectors.toSet());}

}

