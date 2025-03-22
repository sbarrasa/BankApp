package com.sbarrasa.bank.product;

import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.bank.product.types.CheckingAccount;
import com.sbarrasa.bank.product.types.CreditCard;
import com.sbarrasa.bank.product.types.DebitCard;
import com.sbarrasa.bank.product.types.SavingAccount;
import com.sbarrasa.bank.service.ProductsMatcher;
import com.sbarrasa.util.matcher.MatchType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
  ProductEntity productTC_VISA = new CreditCard()
    .setCreditLimit(6000000.00)
    .setTier("GOLD")
    .setBranch(Branch.VISA)
    .setProductType(ProductType.TC);

  ProductEntity productTC_AMEX = new CreditCard()
    .setCreditLimit(5000000.00)
    .setBranch(Branch.AMEX)
    .setProductType(ProductType.TC);

  ProductEntity productTD = new DebitCard()
    .setBranch(Branch.VISA)
    .setProductType(ProductType.TD);

  ProductEntity productCC = new CheckingAccount()
    .setCreditLimit(1000000.00)
    .setCurrency(Currency.ARS)
    .setProductType(ProductType.CC);

  ProductEntity productCA_USD = new SavingAccount()
    .setCurrency(Currency.USD)
    .setProductType(ProductType.CA)

  @Test
  void anyMatch() {
    var sample = new ProductDTO()
      .setBranch(Branch.VISA)
      .setProductType(ProductType.TC);

    var matcher = new ProductsMatcher();

    assertTrue(matcher.match(productTC_VISA, sample, MatchType.ANY));
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
  void getDescription() {
    assertEquals(ProductType.TD.getDescription(), productTD.getDescription());
    assertEquals(ProductType.TC.getDescription(), productTC_VISA.getDescription());

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