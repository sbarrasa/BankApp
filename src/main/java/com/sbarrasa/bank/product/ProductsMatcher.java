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
      .filter(product -> match(product, productSample) == matchStatus)
      .collect(Collectors.toSet());
  }

  public boolean exists(Product product) {
    return !filter(product).isEmpty();
  }

  public static boolean match(Product product1, Product product2) {
    if(product2 == null
    || product1 == null)
      return false;

    return matchAttribute(product1.getProductType(), product2.getProductType())
      && matchAttribute(product1.getBranch(), product2.getBranch())
      && matchAttribute(product1.getTier(), product2.getTier())
      && matchAttribute(product1.getCbu(), product2.getCbu())
      && matchAttribute(product1.getCurrency(), product2.getCurrency())
      && matchAttribute(product1.getIsCredit(), product2.isCredit);
  }

  private static boolean matchAttribute(Object attribute, Object sampleAttribute) {
    return sampleAttribute == null || sampleAttribute.equals(attribute);
  }

}
