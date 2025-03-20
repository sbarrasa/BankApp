package com.sbarrasa.bank.product;

import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.bank.product.types.InvalidProductData;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductValidateTest {
  @Test
  void validateNoProductType() {
    var product = new ProductDTO();

    assertThrows(ProductException.class, product::validate);
  }

  @Test
  void validateDebitCard(){
    var product = new ProductDTO()
      .setProductType(ProductType.TC);

    var ex = assertThrows(InvalidProductData.class, product::validate);
    assertEquals(Set.of("creditLimit", "branch") , ex.getMissingAttributes());

  }

  @Test
  void validateSaveingAccount(){
    var product = new ProductDTO()
      .setProductType(ProductType.CA);

    var ex = assertThrows(InvalidProductData.class, product::validate);
    assertEquals(Set.of("cbu", "currency") , ex.getMissingAttributes());

  }

}
