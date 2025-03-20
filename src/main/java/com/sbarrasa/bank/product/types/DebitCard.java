package com.sbarrasa.bank.product.types;

import com.sbarrasa.bank.product.Product;

public class DebitCard extends Card {

  public DebitCard(Product product) {
    super(product);
  }

  @Override
  public String getName() {
    return "Tarjeta de débito";
  }



}
