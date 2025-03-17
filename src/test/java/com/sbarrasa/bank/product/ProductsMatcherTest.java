package com.sbarrasa.bank.product;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProductsMatcherTest {

  @Test
  void except() {
    var matcher = new ProductsMatcher(SampleProducts.products);
    var sampleProduct = new Product()
      .setBranch(Branch.VISA);

    var excepted = matcher.except(sampleProduct);

    assertEquals(3, excepted.size());
  }

  @Test
  void filter() {
    var matcher = new ProductsMatcher(SampleProducts.products);
    var sampleProduct = new Product()
      .setBranch(Branch.VISA);

    var filtered = matcher.filter(sampleProduct);

    assertEquals(2, filtered.size());
  }

  
  @Test
  void exists() {
    var matcher = new ProductsMatcher(SampleProducts.products);
    var sample = new Product()
      .setProductType(ProductType.TC);

    assertTrue(matcher.exists(sample));

  }

  @Test
  void netExists() {
    var matcher = new ProductsMatcher(Set.of(SampleProducts.productTC, SampleProducts.productTD));
    
    assertFalse(matcher.exists(SampleProducts.productCA_USD));
    
  }

  }