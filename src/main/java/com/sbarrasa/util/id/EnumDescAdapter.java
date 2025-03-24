package com.sbarrasa.util.id;


public final class EnumDescAdapter<E extends Enum<E> & Desc> implements IdDesc<E> {
  private final E enumType;

  public EnumDescAdapter(E enumType) {this.enumType = enumType;}

  @Override
  public String getDescription() {
    return enumType.getDescription();
  }

  @Override
  public E getId() {
    return enumType;
  }


}
