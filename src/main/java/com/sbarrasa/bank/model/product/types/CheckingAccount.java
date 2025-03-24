package com.sbarrasa.bank.model.product.types;

import com.sbarrasa.bank.model.product.ProductType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(CheckingAccount.PRODUCT_TYPE)
@Data
@Accessors(chain = true)
public class CheckingAccount extends Account implements CreditProduct {
  public static final String PRODUCT_TYPE = "CC";
  public static final String NAME = "Cuenta corriente";

  @NotNull
  @Min(1)
  private Double creditLimit;

  @Override
  public ProductType getProductType() {
    return ProductType.valueOf(PRODUCT_TYPE);
  }

  @Override
  public String getName() {
    return NAME;
  }
}
