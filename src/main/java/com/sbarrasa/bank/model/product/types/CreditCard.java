package com.sbarrasa.bank.model.product.types;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("TC")
@Accessors(chain = true)
public class CreditCard extends Card implements CreditProduct {
  @NotNull
  @Min(1)
  private Double creditLimit;

  @Column(length = 10)
  private String tier;

  @Override
  public String getDescription() {
    return super.getDescription() +getTierIfNotNull();
  }

  @Override
  public String getProductDescription() {
    return "Tarjeta de crédito";
  }

  private String getTierIfNotNull() {
    return tier == null
      ? ""
      : " " +tier;
  }

}
