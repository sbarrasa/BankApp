package com.sbarrasa.util.factory;

public interface Factory<P, T> {
  T create(P parameter);
}
