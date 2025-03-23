package com.sbarrasa.bank.model.product.types;

import com.sbarrasa.bank.model.product.ProductType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.experimental.Accessors;

@Entity
@DiscriminatorValue("CA")
@Accessors(chain = true)
public class SavingAccount extends Account {
  @Override
  protected ProductType defaultProductType() {
    return ProductType.CA;
  }
}
