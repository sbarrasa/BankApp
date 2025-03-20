package com.sbarrasa.bank.product;

import com.sbarrasa.bank.util.identifiable.Describable;
import lombok.Getter;

@Getter
public enum Currency implements Describable {
  USD("dólares"),
  ARS("pesos"),
  EUR("euros");

  private final String description;

  Currency(String description) {
    this.description = description;
  }

}
