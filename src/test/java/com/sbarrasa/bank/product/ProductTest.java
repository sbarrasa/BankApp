package com.sbarrasa.bank.product;

import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.util.matcher.MatchType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
  ProductEntity productTC_VISA = new ProductEntity()
    .setProductType(ProductType.TC)
    .setBranch(Branch.VISA)
    .setTier("GOLD")
    .setCreditLimit(6000000.00);

  ProductEntity productTC_AMEX = new ProductEntity()
    .setProductType(ProductType.TC)
    .setBranch(Branch.AMEX)
    .setCreditLimit(5000000.00);

  ProductEntity productTD = new ProductEntity()
    .setProductType(ProductType.TD)
    .setBranch(Branch.VISA);

  ProductEntity productCC = new ProductEntity()
    .setProductType(ProductType.CC)
    .setCurrency(Currency.ARS)
    .setCreditLimit(1000000.00);

  ProductEntity productCA_USD = new ProductEntity()
    .setProductType(ProductType.CA)
    .setCurrency(Currency.USD);

  @Test
  void anyMatch() {
    var sample = new ProductDTO()
      .setBranch(Branch.VISA)
      .setProductType(ProductType.TC);

    assertTrue(productTC_VISA.match(sample, MatchType.ANY));
    assertTrue(productTD.match(sample, MatchType.ANY));
    assertTrue(productTC_AMEX.match(sample, MatchType.ANY));
    assertFalse(productCC.match(sample, MatchType.ANY));

  }

  @Test
  void matchBranch() {
    var sample = new ProductDTO()
      .setBranch(Branch.VISA);

    assertTrue(productTC_VISA.match(sample, MatchType.ALL));
    assertTrue(productTD.match(sample, MatchType.ALL));
    assertFalse(productTC_AMEX.match(sample, MatchType.ALL));

  }

  @Test
  void matchCredit() {
    var sample = new ProductDTO()
      .setIsCredit(true);

    assertTrue(productCC.match(sample, MatchType.ALL));
    assertTrue(productTC_VISA.match(sample, MatchType.ALL));
    assertFalse(productCA_USD.match(sample, MatchType.ALL));

  }


  @Test
  void getName(){
    assertEquals(ProductType.TD.getDescription(), productTD.getName());
    assertEquals(ProductType.TC.getDescription(), productTC_VISA.getName());

  }

  @Test
  void assign() {
    assertEquals("GOLD", productTC_VISA.getTier());
    assertNotNull(productTC_VISA.getCreditLimit());

    productTC_VISA.assign(new ProductEntity().setTier("SIGNATURE"));
    assertEquals("SIGNATURE", productTC_VISA.getTier());
    assertNotNull(productTC_VISA.getCreditLimit());
  }
}