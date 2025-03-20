package com.sbarrasa.bank.product.types;

import com.sbarrasa.bank.product.Product;

public class CheckingAccount extends Account {

  public CheckingAccount(Product product) {
    super(product);
    requiredAttributes().put("creditLimit", Product::getCbu);
  }

  @Override
  public String getName() {
    return "Cuenta corriente";
  }


}
