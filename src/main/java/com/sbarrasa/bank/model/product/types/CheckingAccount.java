package com.sbarrasa.bank.model.product.types;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("CC")
@Data
@Accessors(chain = true)
public class CheckingAccount extends Account implements CreditProduct {
  @NotNull
  @Min(1)
  private Double creditLimit;

  @Override
  public String getProductDescription() {
    return "Cuenta corriente";
  }
}
