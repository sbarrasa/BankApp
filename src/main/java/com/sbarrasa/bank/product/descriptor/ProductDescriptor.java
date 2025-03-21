package com.sbarrasa.bank.product.descriptor;

import com.sbarrasa.bank.product.Product;
import com.sbarrasa.util.matcher.GetFuntion;
import lombok.Getter;

import java.util.*;

@Getter
public abstract class ProductDescriptor implements ProductInfo {
  private final Product product;

  private final Map<String, GetFuntion<Product>> requiredAttributes = new HashMap<>();

  public ProductDescriptor(Product product) {
    this.product = product;
  }

}
