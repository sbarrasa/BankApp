package com.sbarrasa.bank.product;

import com.sbarrasa.bank.product.base.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SavingAccount extends Account {

  @Override
  public String getProduct_type() {
    return "CA";
  }
}
