package com.sbarrasa.bank.product;

import java.util.Set;

class SampleProducts {
  static final Product productTC_VISA = new Product()
    .setProductType(ProductType.TC)
    .setBranch(Branch.VISA)
    .setTier("GOLD")
    .setCreditLimit(6000000.00);

  static final Product productTC_AMEX = new Product()
    .setProductType(ProductType.TC)
    .setBranch(Branch.AMEX)
    .setCreditLimit(5000000.00);

  static final Product productTD = new Product()
    .setProductType(ProductType.TD)
    .setBranch(Branch.VISA);

  static final Product productCC = new Product()
    .setProductType(ProductType.CC)
    .setCurrency(Currency.ARS)
    .setCreditLimit(1000000.00);

  static final Product productCA_ARS = new Product()
    .setProductType(ProductType.CA)
    .setCurrency(Currency.ARS);

  static final Product productCA_USD = new Product()
    .setProductType(ProductType.CA)
    .setCurrency(Currency.USD);

  static final Set<Product> products = Set.of(
    productTC_VISA, productTC_AMEX, productTD, productCC, productCA_ARS, productCA_USD
  );

}
