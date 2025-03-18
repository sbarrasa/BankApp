package com.sbarrasa.bank.product;

import com.sbarrasa.bank.matcher.Match;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

  @Test
  void anyMatch() {
    var sample = new Product()
      .setBranch(Branch.VISA)
      .setProductType(ProductType.TC);

    assertTrue(SampleProducts.productTC_VISA.match(sample, Match.ANY));
    assertTrue(SampleProducts.productTD.match(sample, Match.ANY));
    assertTrue(SampleProducts.productTC_AMEX.match(sample, Match.ANY));
    assertFalse(SampleProducts.productCC.match(sample, Match.ANY));

  }

  @Test
  void matchBranch() {
    var sample = new Product()
      .setBranch(Branch.VISA);

    assertTrue(SampleProducts.productTC_VISA.match(sample, Match.ALL));
    assertTrue(SampleProducts.productTD.match(sample, Match.ALL));
    assertFalse(SampleProducts.productTC_AMEX.match(sample, Match.ALL));

  }

  @Test
  void matchCredit() {
    var sample = new Product()
      .setIsCredit(true);

    assertTrue(SampleProducts.productCC.match(sample, Match.ALL));
    assertTrue(SampleProducts.productTC_VISA.match(sample, Match.ALL));
    assertFalse(SampleProducts.productCA_USD.match(sample, Match.ALL));

  }


  @Test
  void getName(){
    assertEquals(ProductType.TD.getDescription(), SampleProducts.productTD.getName());
    assertEquals(ProductType.TC.getDescription(), SampleProducts.productTC_VISA.getName());

  }
}