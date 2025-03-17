package com.sbarrasa.bank.product;

import lombok.Getter;

@Getter
public enum Branch {
  VISA("Visa"),
  MC("MasterCard"),
  AMEX("Américan Express");

  private final String name;

  Branch(String name) {
    this.name = name;
  }

}
