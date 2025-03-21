package com.sbarrasa.bank.product.descriptor;

import com.sbarrasa.bank.product.Product;

public class SavingAccount extends Account {
  public SavingAccount(Product product) {
    super(product);
  }

  @Override
  public String getDescription() {
    return "Caja de ahorro";
  }

}
