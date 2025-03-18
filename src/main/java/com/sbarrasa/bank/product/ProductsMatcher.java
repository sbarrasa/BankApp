package com.sbarrasa.bank.product;

import com.sbarrasa.bank.util.matcher.MatchType;
import com.sbarrasa.bank.util.matcher.ObjectMatcher;


public class ProductsMatcher extends ObjectMatcher<Product> {


  public ProductsMatcher(){
    super(
      Product::getProductType,
      Product::getBranch,
      Product::getTier,
      Product::getCbu,
      Product::getCurrency
    );
  }


 @Override
 public boolean match(Product product, Product productSample, MatchType matchType) {
    return super.match(product, productSample, matchType)
      && (productSample.isCredit == null
      || productSample.isCredit == product.getIsCredit()) ;
  }

}
