package com.sbarrasa.bank.model.product;

import com.sbarrasa.bank.config.ProductFactoryConfig;
import com.sbarrasa.bank.model.product.types.CreditCard;
import com.sbarrasa.bank.service.ProductFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductFactoryTest {
  ProductFactory productFactory = new ProductFactoryConfig().productFactory();

  @Test
  void create(){
    var product1 = new CreditCard();
    CreditCard product2 = productFactory.create(CreditCard.PRODUCT_TYPE);

    assertEquals(CreditCard.PRODUCT_TYPE, product2.getProductType());
    assertEquals(product1.getClass(), product2.getClass() );

  }

}
