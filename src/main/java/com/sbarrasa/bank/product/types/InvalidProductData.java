package com.sbarrasa.bank.product.types;

import com.sbarrasa.bank.product.ProductException;
import com.sbarrasa.bank.product.ProductType;
import lombok.Getter;

import java.util.Set;

@Getter
public class InvalidProductData extends ProductException {
  private final ProductType productType;
  private final Set<String> missingAttributes;

  public InvalidProductData(ProductType productType, Set<String> missingAttributes) {
    super(null);
    this.productType = productType;
    this.missingAttributes = missingAttributes;
  }

  @Override
  public String getMessage() {
    return "%s requiere %s".formatted(productType, missingAttributes);
  }

}
