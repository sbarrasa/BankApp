package com.sbarrasa.bank.product;

import com.sbarrasa.bank.matcher.Match;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProductsMatcherTest {

  @Test
  void except() {
    var matcher = new ProductsMatcher();
    var sampleProduct = new Product()
      .setBranch(Branch.VISA);

    var excepted = matcher.filter(SampleProducts.products, sampleProduct, Match.EXCEPT);

    assertEquals(4, excepted.size());
  }

  @Test
  void all() {
    var matcher = new ProductsMatcher();
    var sampleProduct = new Product()
      .setBranch(Branch.VISA);

    var filtered = matcher.filter(SampleProducts.products, sampleProduct, Match.ALL);

    assertEquals(2, filtered.size());
  }

  
  @Test
  void exists() {
    var matcher = new ProductsMatcher();
    var sample = new Product()
      .setProductType(ProductType.TC);

    assertFalse(matcher.filter(SampleProducts.products, sample, Match.ALL).isEmpty());

  }

  @Test
  void notExists() {
    var matcher = new ProductsMatcher();
    var products  = Set.of(SampleProducts.productTC_VISA, SampleProducts.productTD);
    
    assertFalse(matcher.filter(products, SampleProducts.productCA_USD, Match.EXCEPT).isEmpty());
    
  }


}