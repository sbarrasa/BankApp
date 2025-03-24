package com.sbarrasa.util.factory;

public interface Factory<K, O> {
  O create(K key);
}
