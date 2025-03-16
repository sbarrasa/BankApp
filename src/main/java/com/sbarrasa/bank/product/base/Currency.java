package com.sbarrasa.bank.product.base;

import lombok.Getter;

@Getter
public enum Currency {
  USD("dólares"),
  ARS("pesos"),
  EUR("euros");

  private final String description;

  Currency(String description) {
    this.description = description;
  }

}
