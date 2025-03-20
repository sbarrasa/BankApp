package com.sbarrasa.bank.product;

import com.sbarrasa.bank.product.types.*;
import com.sbarrasa.util.id.Desc;
import lombok.Getter;

import java.util.function.Function;

@Getter
public enum ProductType implements Desc {
  TC(CreditCard::new) ,
  TD(DebitCard::new),
  CC(CheckingAccount::new),
  CA(SavingAccount::new);

  private final Function<Product, ProductStrategy> infoCreator;

  ProductType(Function<Product, ProductStrategy> infoCreator) {
    this.infoCreator = infoCreator;
  }

  @Override
  public String getDescription() {
    return getInfo(null).getName();
  }

  public ProductStrategy getInfo(Product product) {
    return infoCreator.apply(product);
  }
}
