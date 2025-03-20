package com.sbarrasa.bank.product.types;

import com.sbarrasa.bank.product.Product;


public class CreditCard extends Card{


  public CreditCard(Product product) {
    super(product);
  }

  @Override
  public String getName() {
    return "Tarjeta de crédito";
  }

  @Override
  public String getDescription() {
    return super.getDescription()+getNotNull(getProduct().getTier());
  }

}
