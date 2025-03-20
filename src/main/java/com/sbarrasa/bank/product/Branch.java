package com.sbarrasa.bank.product;

import com.sbarrasa.bank.util.identifiable.Describable;
import lombok.Getter;

@Getter
public enum Branch implements Describable {
  VISA("Visa"),
  MC("MasterCard"),
  AMEX("Américan Express");

  private final String description;

  Branch(String description) {
    this.description = description;
  }
}
