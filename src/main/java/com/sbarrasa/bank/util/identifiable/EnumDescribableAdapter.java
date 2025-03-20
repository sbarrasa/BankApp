package com.sbarrasa.bank.util.identifiable;


public final class EnumDescribableAdapter<E extends Enum<E> & Describable> implements IdDescribable<E> {
  private final E enumType;

  public EnumDescribableAdapter(E enumType) {this.enumType = enumType;}

  @Override
  public String getDescription() {
    return enumType.getDescription();
  }

  @Override
  public E id() {
    return enumType;
  }


}
