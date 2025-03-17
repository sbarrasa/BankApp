package com.sbarrasa.bank.product;

import com.sbarrasa.bank.descriptible.Descriptible;
import lombok.Getter;

@Getter
public enum Branch implements Descriptible {
  VISA("Visa"),
  MC("MasterCard"),
  AMEX("Américan Express");

  private final String description;

  Branch(String description) {
    this.description = description;
  }
}
