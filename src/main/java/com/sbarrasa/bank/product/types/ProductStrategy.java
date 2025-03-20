package com.sbarrasa.bank.product.types;

import com.sbarrasa.bank.product.Product;

import java.util.Objects;

public abstract class ProductStrategy {
  private final Product product;

  protected ProductStrategy(Product product) {
    this.product = product;
  }

  protected Product getProduct(){
    return product;
  }
  public abstract String getName();
  public abstract String getDescription();


  protected String getNotNull(String text) {
    return Objects.isNull(text)
      ? ""
      : " " + text;
  }


}
