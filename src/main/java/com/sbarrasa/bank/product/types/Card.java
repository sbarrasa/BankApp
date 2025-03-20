package com.sbarrasa.bank.product.types;

import com.sbarrasa.bank.product.Product;

public abstract class Card extends ProductStrategy {

  protected Card(Product product) {
    super(product);
  }


  @Override
  public String getDescription() {
    return getName()+ " " + getProduct().getBranch();
  }
}
