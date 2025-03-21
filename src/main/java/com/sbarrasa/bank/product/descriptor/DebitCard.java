package com.sbarrasa.bank.product.descriptor;

import com.sbarrasa.bank.product.Product;

public class DebitCard extends Card {

  public DebitCard(Product product) {
    super(product);
  }

  @Override
  public String getDescription() {
    return "Tarjeta de débito";
  }



}
