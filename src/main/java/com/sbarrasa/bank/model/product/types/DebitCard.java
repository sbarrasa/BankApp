package com.sbarrasa.bank.model.product.types;

import com.sbarrasa.bank.model.product.ProductType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.experimental.Accessors;

@Entity
@DiscriminatorValue("TD")
@Accessors(chain = true)
public class DebitCard extends Card {

  @Override
  protected ProductType defaultProductType() {
    return ProductType.TD;
  }
}
