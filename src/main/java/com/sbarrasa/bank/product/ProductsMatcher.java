package com.sbarrasa.bank.product;

import com.sbarrasa.util.matcher.ObjectMatcher;


public class ProductsMatcher extends ObjectMatcher<Product> {

  public ProductsMatcher(){
    super(
      Product::getProductType,
      Product::getBranch,
      Product::getTier,
      Product::getCbu,
      Product::getCurrency,
      Product::getIsCredit
    );
  }



}
