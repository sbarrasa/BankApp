package com.sbarrasa.bank.product.base;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Card extends Product {
  private Branch branch;

  @Override
  public String getDescription(){
    return super.getName()+" "+branch;
  }

}
