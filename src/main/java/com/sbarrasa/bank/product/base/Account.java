package com.sbarrasa.bank.product.base;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Account extends Product {
  private String cbu;
  private Currency currency;

  @Override
  public String getDescription(){
    return super.getName()+" en "+currency.getDescription();
  }

}