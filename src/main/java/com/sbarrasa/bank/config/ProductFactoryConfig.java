package com.sbarrasa.bank.config;

import com.sbarrasa.bank.model.product.types.CheckingAccount;
import com.sbarrasa.bank.model.product.types.CreditCard;
import com.sbarrasa.bank.model.product.types.DebitCard;
import com.sbarrasa.bank.model.product.types.SavingAccount;
import com.sbarrasa.bank.service.ProductFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductFactoryConfig {
  @Bean
  public ProductFactory productFactory() {
    return new ProductFactory()
      .register(CreditCard::new)
      .register(DebitCard::new)
      .register(CheckingAccount::new)
      .register(SavingAccount::new);


  }
}
