package com.sbarrasa.bank.product;

import java.util.Objects;

public class ProductDescriptionBuilder {
  private final Product product;

  public ProductDescriptionBuilder(Product product) {
    this.product = product;
  }

  public String build() {
    Objects.requireNonNull(product.getProductType());

    ProductType productType = product.getProductType();

    return switch (productType) {
      case TC -> productType.getDescription() + " " + product.getBranch().getDescription() + " " + product.getTier();
      case TD -> productType.getDescription() + " " + product.getBranch().getDescription();
      case CC, CA -> productType.getDescription() + " en " + product.getCurrency().getDescription();
    };
  }
}

