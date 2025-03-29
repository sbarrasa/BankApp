package com.sbarrasa.bank.model.product;

import org.junit.jupiter.api.Test;

import static com.sbarrasa.bank.model.product.ProductSamples.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductEntityTest {

  @Test
  void getIsCredit(){
    assertTrue(productTC_VISA.getIsCredit());
    assertFalse(productCA_USD.getIsCredit());
  }

}