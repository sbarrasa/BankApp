package com.sbarrasa.bank.customer;

import com.sbarrasa.bank.util.descriptible.Descriptible;
import lombok.Getter;


@Getter
public enum Gender implements Descriptible {
  F("Femenino"),
  M("Masculino"),
  X("No definido");

  private final String description;

  Gender(String description) {
    this.description = description;
  }
}
