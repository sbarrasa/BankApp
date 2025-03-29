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
@DiscriminatorValue(CreditCard.PRODUCT_TYPE)
@Accessors(chain = true)
public class CreditCard extends Card implements CreditProduct {
  public static final String PRODUCT_TYPE = "TC";
  public static final String NAME = "Tarjeta de crédito";

  @NotNull
  @Min(1)
  private Double creditLimit;

  @Column(length = 10)
  private String tier;

  @Override
  public String getProductType() {
    return PRODUCT_TYPE;
  }

  @Override
  public String getDescription() {
    return super.getDescription() + getTierIfNotNull();
  }

  @Override
  public String getName() {
    return NAME;
  }

  private String getTierIfNotNull() {
    return tier == null
      ? ""
      : " " + tier;
  }

}
