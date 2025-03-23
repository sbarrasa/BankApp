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
      .setBranch(Branch.VISA)
      .setProductType(ProductType.TC);
  }

  static CreditCard productTC_AMEX = new CreditCard();
  static {
    productTC_AMEX
      .setCreditLimit(5000000.00)
      .setBranch(Branch.AMEX)
      .setProductType(ProductType.TC);
  }

  static DebitCard productTD = new DebitCard();
  static {
    productTD
      .setBranch(Branch.VISA)
      .setProductType(ProductType.TD);
  }

  static CheckingAccount productCC = new CheckingAccount();
  static {
    productCC
      .setCreditLimit(1000000.00)
      .setCbu("1234")
      .setCurrency(Currency.ARS)
      .setProductType(ProductType.CC);

  }

  static SavingAccount productCA_USD = new SavingAccount();
  static {
    productCA_USD
      .setCbu("4321")
      .setCurrency(Currency.USD)
      .setProductType(ProductType.CA);
  }


  @Test
  void getIsCredit(){
    assertTrue(productTC_VISA.getIsCredit());
    assertFalse(productCA_USD.getIsCredit());
  }

  @Test
  void getDescription() {
    assertTrue(productTD.getDescription().startsWith(productTD.getProductType().getDescription()));
  }

}