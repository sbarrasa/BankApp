package com.sbarrasa.bank.product;

import com.sbarrasa.bank.util.descriptible.Descriptible;
import lombok.Getter;

@Getter
public enum ProductType implements Descriptible {
  TC("Tarjeta de crédito") ,
  TD("Tarjeta de débito"),
  CC("Cuenta corriente"),
  CA("Caja de ahorro");

  private final String description;

  ProductType(String description) {
    this.description = description;
  }


}
