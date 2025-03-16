package com.sbarrasa.bank.product;

import com.sbarrasa.bank.product.base.Card;
import com.sbarrasa.bank.product.base.CreditProduct;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreditCard extends Card implements CreditProduct {
  private Double limit;
  private String tier;

  @Override
  public String getProduct_type() {
    return "TC";
  }

  @Override
  public String getDescription() {
    return super.getDescription()+" "+tier;
  }
}
