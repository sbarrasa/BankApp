package com.sbarrasa.bank.product;

import com.sbarrasa.util.id.Desc;
import lombok.Getter;

@Getter
public enum ProductType implements Desc {
  TC("Tarjeta de crédito") ,
  TD("Tarjeta de débito"),
  CC("Cuenta corriente"),
  CA("Caja de ahorro");

  private final String description;

  ProductType(String description) {
    this.description = description;
  }

}
