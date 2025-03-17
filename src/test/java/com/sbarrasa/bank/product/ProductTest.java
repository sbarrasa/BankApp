package com.sbarrasa.bank.product;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
  Product productTC = new Product()
    .setProductType(ProductType.TC)
    .setBranch(Branch.VISA)
    .setTier("GOLD")
    .setCreditLimit(6000000.00);


  Product productTD = new Product()
    .setProductType(ProductType.TD)
    .setBranch(Branch.VISA);

  Product productCC = new Product()
    .setProductType(ProductType.CC)
    .setCurrency(Currency.ARS)
    .setCreditLimit(1000000.00);

  Product productCA_ARS = new Product()
    .setProductType(ProductType.CA)
    .setCurrency(Currency.ARS);

  Product productCA_USD = new Product()
    .setProductType(ProductType.CA)
    .setCurrency(Currency.USD);

  List<Product> products = List.of(
    productTC, productTD, productCC, productCA_ARS, productCA_ARS
  );

  @Test
  void matchARS() {
    var sample = new Product()
      .setCurrency(Currency.ARS);

    assertTrue(productCA_ARS.match(sample));
    assertFalse(productTC.match(sample));
    assertFalse(productCA_USD.match(sample));

  }

  @Test
  void matchCredit() {
    var sample = new Product()
      .setIsCredit(true);

    assertTrue(productCC.match(sample));
    assertTrue(productTC.match(sample));
    assertFalse(productCA_USD.match(sample));

  }

  @Test
  void matchAny() {
    var sample = new Product()
      .setBranch(Branch.VISA)
      .setProductType(ProductType.TC);

    assertTrue(productTC.match(sample));
    assertFalse(productTD.match(sample));
    assertFalse(productCC.match(sample));

  }
  @Test
  void filter(){
    var sample = new Product()
      .setBranch(Branch.VISA);

    var filtered = products.stream()
      .filter(product -> product.match(sample))
      .toList();

    assertEquals(2, filtered.size());

  }

  @Test
  void getName(){
    assertEquals(ProductType.TD.getName(), productTD.getName());
    assertEquals(ProductType.TC.getName(), productTC.getName());

  }
}