package com.sbarrasa.bank.config;

import com.sbarrasa.bank.model.product.types.*;
import com.sbarrasa.bank.service.ProductFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductFactoryConfig {
  @Bean
  public ProductFactory productFactory() {
    return new ProductFactory()
      .register(new ProductFactory.Descriptor(CreditCard.PRODUCT_TYPE, CreditCard.NAME, CreditCard::new))
      .register(new ProductFactory.Descriptor(DebitCard.PRODUCT_TYPE, DebitCard.NAME, DebitCard::new))
      .register(new ProductFactory.Descriptor(CheckingAccount.PRODUCT_TYPE, CheckingAccount.NAME, CheckingAccount::new))
      .register(new ProductFactory.Descriptor(SavingAccount.PRODUCT_TYPE, SavingAccount.NAME, SavingAccount::new));
  }
}
