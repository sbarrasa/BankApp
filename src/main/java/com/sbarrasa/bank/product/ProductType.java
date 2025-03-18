package com.sbarrasa.bank.product;

import com.sbarrasa.bank.util.descriptible.Describable;
import lombok.Getter;

@Getter
public enum ProductType implements Describable {
  TC("Tarjeta de crédito") ,
  TD("Tarjeta de débito"),
  CC("Cuenta corriente"),
  CA("Caja de ahorro");

  private final String description;

  ProductType(String description) {
    this.description = description;
  }


}
