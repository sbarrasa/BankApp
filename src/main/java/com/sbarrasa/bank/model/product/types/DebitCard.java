package com.sbarrasa.bank.model.product.types;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.experimental.Accessors;

@Entity
@DiscriminatorValue(DebitCard.PRODUCT_TYPE)
@Accessors(chain = true)
public class DebitCard extends Card {
  public static final String PRODUCT_TYPE = "TD";
  public static final String NAME = "Tarjeta de débito";

  @Override
  public String getProductType() {
    return PRODUCT_TYPE;
  }

  @Override
  public String getName() {
    return NAME;
  }
}
