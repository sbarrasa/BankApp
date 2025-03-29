package com.sbarrasa.bank.service;

import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.bank.model.product.ProductEntity;
import com.sbarrasa.util.matcher.MatchType;
import com.sbarrasa.util.matcher.ObjectMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductMatcher extends ObjectMatcher<ProductDTO> {
  private final ProductAdapter productAdapter;

  public ProductMatcher() {
    this(new ProductAdapter());
  }

  @Autowired
  public ProductMatcher(ProductAdapter productAdapter) {
    super(
      ProductDTO::getProductType,
      ProductDTO::getBranch,
      ProductDTO::getCbu,
      ProductDTO::getCurrency,
      ProductDTO::getIsCredit
    );
    this.productAdapter = productAdapter;
  }

  public boolean match(ProductEntity product, ProductDTO sampleObject, MatchType matchType) {
    return match(productAdapter.toDTO(product), sampleObject, matchType);
  }
}
