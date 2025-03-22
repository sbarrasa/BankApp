package com.sbarrasa.bank.model.product;

import com.sbarrasa.util.id.Desc;
import lombok.Getter;

@Getter
public enum Currency implements Desc {
  USD("dólares"),
  ARS("pesos"),
  EUR("euros");

  private final String description;

  Currency(String description) {
    this.description = description;
  }

}
