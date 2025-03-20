package com.sbarrasa.bank.util.identifiable;

public interface Identifiable<T> {
  T id();
  default T getId(){
    return id();
  }

}
