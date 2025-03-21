package com.sbarrasa.bank.product;

import com.sbarrasa.bank.product.descriptor.AttributeRequiredException;
import com.sbarrasa.util.matcher.GetFuntion;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductValidator {
  private final Product product;

  ProductValidator(Product product){
    this.product = product;
  }


  public void validate() {
    validate(product.getProductType());
    validate(product.getProductType()
      .getDescriptor()
      .getRequiredAttributes());

  }

  private void validate(ProductType productType) {
    if(productType == null)
      throw new AttributeRequiredException("Product", Set.of("productType"));
  }

  private void validate(Map<String, GetFuntion<Product>> requiredAttributes) {
    var missingAttributes = requiredAttributes.entrySet().stream()
      .filter(entry -> entry.getValue().apply(product) == null)
      .map(Map.Entry::getKey)
      .collect(Collectors.toSet());

    if (!missingAttributes.isEmpty())
      throw new AttributeRequiredException(product.getProductType(), missingAttributes);

  }



}
