package com.sbarrasa.bank.product.types;

import com.sbarrasa.bank.product.Product;

public class SavingAccount extends Account {
  public SavingAccount(Product product) {
    super(product);
  }

  @Override
  public String getName() {
    return "Caja de ahorro";
  }

}
