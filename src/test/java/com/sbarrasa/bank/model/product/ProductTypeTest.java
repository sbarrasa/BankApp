package com.sbarrasa.bank.model.product;

import com.sbarrasa.bank.model.product.types.CreditCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTypeTest {
  @Test
  public void create() {
    CreditCard product = ProductType.TC.createProduct();
    assertEquals(ProductType.TC, product.getProductType());
    assertTrue(product.getIsCredit());
    assertNull(product.getBranch());
  }

  @Test
  public void getDescription() {
    var product = ProductType.TD.createProduct();
    assertEquals(ProductType.TD.getDescription(), product.getProductDescription());
  }
}
