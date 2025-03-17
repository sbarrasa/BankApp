package com.sbarrasa.bank.product;

import com.sbarrasa.bank.descriptible.Descriptible;
import lombok.Getter;

@Getter
public enum ProductType implements Descriptible {
  TC("Tarjeta de credito") ,
  TD("Tarjeta de débito"),
  CC("Cuenta corriente"),
  CA("Caja de ahorro");

  private final String description;

  ProductType(String description) {
    this.description = description;
  }


}
