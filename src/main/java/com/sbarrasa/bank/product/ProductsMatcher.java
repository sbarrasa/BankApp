package com.sbarrasa.bank.product;

import java.util.Set;
import java.util.stream.Collectors;

public class ProductsMatcher {

  private final Set<Product> products;

  public ProductsMatcher(Set<Product> products){
    this.products = products;
  }
  public Set<Product> except(Product productSample){
    return filter(productSample, false);
  }

  public Set<Product> filter(Product productSample){
    return filter(productSample, true);
  }

  private Set<Product> filter(Product productSample, boolean matchStatus){
    return products.stream()
      .filter(product -> product.match(productSample) == matchStatus)
      .collect(Collectors.toSet());
  }

  public boolean exists(Product product) {
    return !filter(product).isEmpty();
  }
}
