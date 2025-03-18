package com.sbarrasa.bank.util.descriptible;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumDescriptionSet<E extends Enum<E> & Descriptible>{
  private final Class<E> enumType;

  public EnumDescriptionSet(Class<E> enumType) {
    this.enumType = enumType;
  }

  public Set<EnumDescription<E>> listAll(){
    return Arrays.stream(enumType.getEnumConstants())
      .map(enumConstant -> new EnumDescription<>(
        enumConstant,
        enumConstant.getDescription()))
      .collect(Collectors.toSet());}

}

