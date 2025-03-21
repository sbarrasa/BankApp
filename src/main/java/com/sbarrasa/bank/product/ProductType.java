package com.sbarrasa.bank.product;

import com.sbarrasa.bank.product.descriptor.*;
import com.sbarrasa.util.factory.Factory;
import com.sbarrasa.util.id.Desc;
import lombok.Getter;

@Getter
public enum ProductType implements Desc {
  TC(CreditCard::new) ,
  TD(DebitCard::new),
  CC(CheckingAccount::new),
  CA(SavingAccount::new);

  private final Factory<Product, ProductDescriptor> descriptorCreator;

  ProductType(Factory<Product, ProductDescriptor> descriptorCreator) {
    this.descriptorCreator = descriptorCreator;
  }

  @Override
  public String getDescription() {
    return getDescriptor().getDescription();
  }

  public ProductDescriptor getDescriptor() {
    return getDescriptor(null);
  }

  public ProductDescriptor getDescriptor(Product product) {
    return descriptorCreator.create(product);
  }
}
