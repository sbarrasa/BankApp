package com.sbarrasa.util.id;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumIdDesc<E extends Enum<E> & Desc> {
  private final Class<E> enumType;

  public EnumIdDesc(Class<E> enumType) {
    this.enumType = enumType;
  }

  public Set<IdDesc<E>> asSet() {
    return Arrays.stream(enumType.getEnumConstants())
      .map(EnumIdDescAdapter::new)
      .collect(Collectors.toSet());
  }

  private static final class EnumIdDescAdapter<E extends Enum<E> & Desc> implements IdDesc<E> {
    private final E enumType;

    public EnumIdDescAdapter(E enumType) {this.enumType = enumType;}

    @Override
    public String getDescription() {
      return enumType.getDescription();
    }

    @Override
    public E getId() {
      return enumType;
    }


  }


}

