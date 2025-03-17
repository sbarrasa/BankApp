package com.sbarrasa.bank.product;

import lombok.Getter;

@Getter
public enum ProductType {
  TC("Tarjeta de credito") ,
  TD("Tarjeta de débito"),
  CC("Cuenta corriente"),
  CA("Caja de ahorro");

  private final String name;

  ProductType(String name) {
    this.name = name;
  }


}
