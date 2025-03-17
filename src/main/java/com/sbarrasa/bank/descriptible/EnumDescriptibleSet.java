package com.sbarrasa.bank.descriptible;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumDescriptibleSet<E extends Enum<E> & Descriptible>{
  Class<E> enumType;

  public EnumDescriptibleSet(Class<E> enumType) {
    this.enumType = enumType;
  }

  public Set<EnumDescriptibleDTO<E>> listAll(){
    return Arrays.stream(enumType.getEnumConstants())
      .map(enumConstant -> new EnumDescriptibleDTO<>(
        enumConstant,
        enumConstant.getDescription()))
      .collect(Collectors.toSet());}

}

