package com.sbarrasa.bank.product.types;

import com.sbarrasa.bank.product.Product;

public abstract class Card extends ProductStrategy {

  public Card(Product product) {
    super(product);
    requiredAttributes().put("branch", Product::getBranch);
  }


  @Override
  public String getDescription() {
    return getName()+ " " + getProduct().getBranch();
  }


}
