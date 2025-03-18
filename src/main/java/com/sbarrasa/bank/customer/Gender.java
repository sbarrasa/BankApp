package com.sbarrasa.bank.customer;

import com.sbarrasa.bank.util.descriptible.Describable;
import lombok.Getter;


@Getter
public enum Gender implements Describable {
  F("Femenino"),
  M("Masculino"),
  X("No definido");

  private final String description;

  Gender(String description) {
    this.description = description;
  }
}
