package com.sbarrasa.bank.product;

import com.sbarrasa.bank.product.base.Account;
import com.sbarrasa.bank.product.base.CreditProduct;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CheckingAccount extends Account implements CreditProduct {
  private Double limit;

  @Override
  public String getProduct_type() {
    return "CC";
  }
}
