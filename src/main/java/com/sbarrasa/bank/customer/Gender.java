package com.sbarrasa.bank.customer;

public enum Gender {
  F("Femenino"),
  M("Masculino"),
  X("No definido");

  private final String description;

  Gender(String description) {
    this.description = description;
  }

  public String description() {
    return description;
  }
}
