package com.sbarrasa.bank.product;


import com.sbarrasa.bank.product.base.Card;

public class DebitCard extends Card {
  @Override
  public String getProduct_type() {
    return "TD";
  }
}
