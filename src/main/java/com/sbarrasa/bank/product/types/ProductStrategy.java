package com.sbarrasa.bank.product.types;

import com.sbarrasa.bank.product.Product;
import com.sbarrasa.util.matcher.Getter;

import java.util.*;
import java.util.stream.Collectors;


public abstract class ProductStrategy {
  private final Product product;
  private final Map<String, Getter<Product>> requiredAttributes = new HashMap<>();

  public ProductStrategy(Product product) {
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

  public Map<String, Getter<Product>> requiredAttributes(){
    return requiredAttributes;
  }

  private Set<String> getMissingAttributes(Product product) {
    return requiredAttributes().entrySet().stream()
      .filter(entry -> entry.getValue().apply(product) == null)
      .map(Map.Entry::getKey)
      .collect(Collectors.toSet());
  }

  public void validate() throws InvalidProductData {
    var missingAttributes = getMissingAttributes(product);
    if (!missingAttributes.isEmpty())
      throw new InvalidProductData(product.getProductType(), missingAttributes);
  }

}
