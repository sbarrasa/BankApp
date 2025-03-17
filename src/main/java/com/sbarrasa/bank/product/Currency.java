package com.sbarrasa.bank.product;

import com.sbarrasa.bank.descriptible.Descriptible;
import lombok.Getter;

@Getter
public enum Currency implements Descriptible {
  USD("dólares"),
  ARS("pesos"),
  EUR("euros");

  private final String description;

  Currency(String description) {
    this.description = description;
  }

}
