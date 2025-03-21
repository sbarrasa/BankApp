package com.sbarrasa.bank.product.descriptor;

import com.sbarrasa.bank.product.Product;

public abstract class Account extends ProductDescriptor {
  protected Account(Product product) {
    super(product);
    requiredAttributes().put("cbu", Product::getCbu);
    requiredAttributes().put("currency", Product::getCurrency);
  }

  @Override
  public String getLargeDescription() {
    return getDescription()+" en "+getProduct().getCurrency().getDescription();
  }


}
