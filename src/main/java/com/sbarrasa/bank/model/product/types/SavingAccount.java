package com.sbarrasa.bank.model.product.types;

import com.sbarrasa.bank.model.product.ProductType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.experimental.Accessors;

@Entity
@DiscriminatorValue(SavingAccount.PRODUCT_TYPE)
@Accessors(chain = true)
public class SavingAccount extends Account {
  public static final String PRODUCT_TYPE = "CA";
  public static final String NAME = "Caja de ahorro";

  @Override
  public ProductType getProductType() {
    return ProductType.valueOf(PRODUCT_TYPE);
  }

  @Override
  public String getName() {
    return NAME;
  }
}
