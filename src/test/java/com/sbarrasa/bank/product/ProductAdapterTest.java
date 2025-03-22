package com.sbarrasa.bank.product;
import com.sbarrasa.bank.service.ProductAdapter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductAdapterTest {
  static ProductAdapter adapter = new ProductAdapter();

  @Test
  void productEntity(){
    var productDTO = adapter.toDTO(ProductEntityTest.productTC_VISA);

    assertEquals(ProductType.TC, productDTO.getProductType() );
  }

  @Test
  void account(){
    var productDTO = adapter.toDTO(ProductEntityTest.productCC);

    assertNull(productDTO.getBranch() );
    assertNotNull(productDTO.getCbu());
    assertEquals(Currency.ARS, productDTO.getCurrency() );
  }

  @Test
  void card(){
    var productDTO = adapter.toDTO(ProductEntityTest.productTC_VISA);

    assertEquals(Branch.VISA, productDTO.getBranch() );
    assertNull(productDTO.getCurrency());
  }

  @Test
  void creditProduct() {
    var productDTO = adapter.toDTO(ProductEntityTest.productTC_VISA);

    assertNotNull(productDTO.getCreditLimit());
    assertNotNull(productDTO.getIsCredit());
  }

  @Test
  void noCreditProduct() {
    var productDTO = adapter.toDTO(ProductEntityTest.productTD);

    assertNull(productDTO.getCreditLimit());
    assertFalse(productDTO.getIsCredit());
  }

  @Test
  void CreditCard(){
    var productDTO = adapter.toDTO(ProductEntityTest.productTC_VISA);
    assertEquals("GOLD", productDTO.getTier() );

    assertEquals(ProductType.TC, productDTO.getProductType() );
    assertEquals(ProductType.TC.getDescription()+ " VISA GOLD", productDTO.getDescription());
  }

  @Test
  void DebitCard(){
    var productDTO = adapter.toDTO(ProductEntityTest.productTD);
    assertEquals(ProductType.TD, productDTO.getProductType() );
    assertEquals(ProductType.TD.getDescription()+" "+ProductEntityTest.productTD.getBranch(),
      productDTO.getDescription());
  }

  @Test
  void checkingAccount(){
    var productDTO = adapter.toDTO(ProductEntityTest.productCC);
    assertEquals(ProductType.CC, productDTO.getProductType() );
    assertEquals(ProductType.CC.getDescription()+" en pesos", productDTO.getDescription());
  }

  @Test
  void savingAccount(){
    var productDTO = adapter.toDTO(ProductEntityTest.productCA_USD);
    assertEquals(ProductType.CA, productDTO.getProductType() );
    assertEquals(ProductType.CA.getDescription()+" en dólares", productDTO.getDescription());
    assertEquals(Currency.USD, productDTO.getCurrency());
 }

}
