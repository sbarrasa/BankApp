package com.sbarrasa.bank.product.descriptor;

import com.sbarrasa.bank.product.Product;

public abstract class Card extends ProductDescriptor {

  public Card(Product product) {
    super(product);
    getRequiredAttributes().put("branch", Product::getBranch);
  }


  @Override
  public String getLargeDescription() {
    return getDescription()+ " " + getProduct().getBranch();
  }


}
