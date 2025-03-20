package com.sbarrasa.bank.product;

import com.sbarrasa.util.id.Desc;
import lombok.Getter;

@Getter
public enum Branch implements Desc {
  VISA("Visa"),
  MC("MasterCard"),
  AMEX("Américan Express");

  private final String description;

  Branch(String description) {
    this.description = description;
  }
}
