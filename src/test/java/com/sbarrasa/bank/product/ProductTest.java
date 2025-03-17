package com.sbarrasa.bank.product;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

  @Test
  void matchAttributeARS() {
    var sample = new Product()
      .setCurrency(Currency.ARS);

    assertTrue(SampleProducts.productCA_ARS.match(sample));
    assertFalse(SampleProducts.productTC.match(sample));
    assertFalse(SampleProducts.productCA_USD.match(sample));

  }

  @Test
  void matchCredit() {
    var sample = new Product()
      .setIsCredit(true);

    assertTrue(SampleProducts.productCC.match(sample));
    assertTrue(SampleProducts.productTC.match(sample));
    assertFalse(SampleProducts.productCA_USD.match(sample));

  }

  @Test
  void matchAny() {
    var sample = new Product()
      .setBranch(Branch.VISA)
      .setProductType(ProductType.TC);

    assertTrue(SampleProducts.productTC.match(sample));
    assertFalse(SampleProducts.productTD.match(sample));
    assertFalse(SampleProducts.productCC.match(sample));

  }
  @Test
  void filter(){
    var sample = new Product()
      .setBranch(Branch.VISA);

    var filtered = SampleProducts.products.stream()
      .filter(product -> product.match(sample))
      .toList();

    assertEquals(2, filtered.size());

  }

  @Test
  void getName(){
    assertEquals(ProductType.TD.getDescription(), SampleProducts.productTD.getName());
    assertEquals(ProductType.TC.getDescription(), SampleProducts.productTC.getName());

  }
}