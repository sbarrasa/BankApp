package com.sbarrasa.bank.product.base;

import lombok.Getter;

@Getter
public enum Branch {
  VISA("Visa"),
  MC("MasterCard"),
  Amex("Américan Express"),
  Cabal("Cabal");

  private final String name;

  Branch(String name) {
    this.name = name;
  }

}
