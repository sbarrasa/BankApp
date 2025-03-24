package com.sbarrasa.bank.model.product;

import com.sbarrasa.bank.model.product.types.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductEntityTest {
  static CreditCard productTC_VISA = new CreditCard();
  static {
    productTC_VISA
      .setCreditLimit(6000000.00)
      .setTier("GOLD")
      .setBranch(Branch.VISA);
  }

  static CreditCard productTC_AMEX = new CreditCard();
  static {
    productTC_AMEX
      .setCreditLimit(5000000.00)
      .setBranch(Branch.AMEX);
  }

  static DebitCard productTD = new DebitCard();
  static {
    productTD
      .setBranch(Branch.VISA);
  }

  static CheckingAccount productCC = new CheckingAccount();
  static {
    productCC
      .setCreditLimit(1000000.00)
      .setCbu("1234")
      .setCurrency(Currency.ARS);

  }

  static SavingAccount productCA_USD = new SavingAccount();
  static {
    productCA_USD
      .setCbu("4321")
      .setCurrency(Currency.USD);
  }


  @Test
  void getIsCredit(){
    assertTrue(productTC_VISA.getIsCredit());
    assertFalse(productCA_USD.getIsCredit());
  }

  @Test
  void productType() {
    assertEquals(ProductType.TC, productTC_VISA.getProductType());
    assertEquals(ProductType.TD, productTD.getProductType());
    assertEquals(ProductType.CA, productCA_USD.getProductType());
    assertEquals(ProductType.CC, productCC.getProductType());
  }

  @Test
  void getDescription() {
    assertTrue(productTD.getDescription().startsWith(productTD.getProductType().getDescription()));
  }

}