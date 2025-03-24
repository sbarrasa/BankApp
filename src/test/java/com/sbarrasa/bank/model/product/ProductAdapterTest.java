package com.sbarrasa.bank.model.product;

import com.sbarrasa.bank.config.ProductFactoryConfig;
import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.bank.model.product.types.*;
import com.sbarrasa.bank.service.ProductAdapter;
import org.junit.jupiter.api.Test;

import static com.sbarrasa.bank.model.product.ProductEntityTest.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductAdapterTest {
  static ProductAdapter adapter = new ProductAdapter(new ProductFactoryConfig().productFactory());

  @Test
  void productEntity() {
    var productDTO = adapter.toDTO(productTC_VISA);

    assertEquals(CreditCard.PRODUCT_TYPE, productDTO.getProductType());
  }

  @Test
  void account() {
    var productDTO = adapter.toDTO(productCC);

    assertNull(productDTO.getBranch());
    assertNotNull(productDTO.getCbu());
    assertEquals(Currency.ARS, productDTO.getCurrency());
  }

  @Test
  void card() {
    var productDTO = adapter.toDTO(productTC_VISA);

    assertEquals(Branch.VISA, productDTO.getBranch());
    assertNull(productDTO.getCurrency());
  }

  @Test
  void creditProduct() {
    var productDTO = adapter.toDTO(productTC_VISA);

    assertNotNull(productDTO.getCreditLimit());
    assertTrue(productDTO.getIsCredit());
    assertNotNull(productDTO.getDescription());
  }

  @Test
  void noCreditProduct() {
    var productDTO = adapter.toDTO(productTD);

    assertNull(productDTO.getCreditLimit());
    assertFalse(productDTO.getIsCredit());
  }

  @Test
  void CreditCard() {
    var productDTO = adapter.toDTO(productTC_VISA);
    assertEquals("GOLD", productDTO.getTier());

    assertEquals(CreditCard.PRODUCT_TYPE, productDTO.getProductType());
    assertEquals(CreditCard.NAME + " VISA GOLD", productDTO.getDescription());
  }

  @Test
  void DebitCard() {
    var productDTO = adapter.toDTO(productTD);
    assertEquals(DebitCard.PRODUCT_TYPE, productDTO.getProductType());
    assertEquals(DebitCard.NAME + " " + productTD.getBranch(),
      productDTO.getDescription());
  }

  @Test
  void checkingAccount() {
    var productDTO = adapter.toDTO(productCC);
    assertEquals(CheckingAccount.PRODUCT_TYPE, productDTO.getProductType());
    assertEquals(CheckingAccount.NAME + " en pesos", productDTO.getDescription());
  }

  @Test
  void savingAccount() {
    var productDTO = adapter.toDTO(productCA_USD);
    assertEquals(SavingAccount.PRODUCT_TYPE, productDTO.getProductType());
    assertEquals(SavingAccount.NAME + " en dólares", productDTO.getDescription());
    assertEquals(Currency.USD, productDTO.getCurrency());
  }

  @Test
  void mapEntityToDTO() {
    var productDTO = new ProductDTO();
    productDTO.setBranch(Branch.VISA);
    productDTO.setTier("GOLD");
    productDTO.setCurrency(Currency.ARS);

    assertEquals(Branch.VISA, productDTO.getBranch());
    assertNull(productDTO.getCreditLimit());

    adapter.map(productTC_AMEX, productDTO);

    assertEquals("GOLD", productDTO.getTier());
    assertEquals(Branch.AMEX, productDTO.getBranch());
    assertEquals(Currency.ARS, productDTO.getCurrency());
    assertNotNull(productDTO.getCreditLimit());

  }

  @Test
  void mapDTOToEntity() {
    var productEntity = new CreditCard();
    productEntity.setBranch(Branch.VISA);

    assertEquals(CreditCard.PRODUCT_TYPE, productEntity.getProductType());
    assertNull(productEntity.getCreditLimit());
    assertNotNull(productEntity.getBranch());

    var productDTO = new ProductDTO();
    productDTO.setCreditLimit(666.66);
    productDTO.setBranch(null);


    adapter.map(productDTO, productEntity);

    assertEquals(CreditCard.PRODUCT_TYPE, productEntity.getProductType());
    assertNotNull(productEntity.getCreditLimit());
    assertNotNull(productEntity.getBranch());

  }

  @Test
  void toEntity(){
    var productDTO = new ProductDTO();
    productDTO.setProductType(CreditCard.PRODUCT_TYPE);
    productDTO.setBranch(Branch.VISA);
    productDTO.setCreditLimit(123.00);

    var productEntity = adapter.toEntity(productDTO);
    assertEquals(CreditCard.PRODUCT_TYPE, productEntity.getProductType());
    assertEquals(CreditCard.class, productEntity.getClass());

  }

}
