package com.sbarrasa.bank.product.descriptor;

import com.sbarrasa.bank.product.Product;


public class CreditCard extends Card{


  public CreditCard(Product product) {
    super(product);
    getRequiredAttributes().put("creditLimit", Product::getCreditLimit);
  }

  @Override
  public String getDescription() {
    return "Tarjeta de crédito";
  }

  @Override
  public String getLargeDescription() {
    return super.getLargeDescription()+getTier();
  }

  private String getTier() {
    return getProduct().getTier()== null
      ? ""
      : " " +getProduct().getTier();

  }

}
