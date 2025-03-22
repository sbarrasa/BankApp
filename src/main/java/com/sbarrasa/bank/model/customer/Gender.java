package com.sbarrasa.bank.model.customer;

import com.sbarrasa.util.id.Desc;
import lombok.Getter;


@Getter
public enum Gender implements Desc {
  F("Femenino"),
  M("Masculino"),
  X("No definido");

  private final String description;

  Gender(String description) {
    this.description = description;
  }
}
