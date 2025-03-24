package com.sbarrasa.util.id;

public interface IdDesc<T> extends Id<T>, Desc {
  static <T> IdDesc<T> of(T id, String description) {
    return new IdDesc<>() {
      @Override
      public String getDescription() {return description;}

      @Override
      public T getId() {return id;}
    };
  }
}
