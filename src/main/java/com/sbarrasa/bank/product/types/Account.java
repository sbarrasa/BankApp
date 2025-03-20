package com.sbarrasa.bank.product.types;

import com.sbarrasa.bank.product.Product;

public abstract class Account extends ProductStrategy {
  protected Account(Product product) {
    super(product);
  }

  @Override
  public String getDescription() {
    return getName()+" en "+getProduct().getCurrency().getDescription();
  }

}
