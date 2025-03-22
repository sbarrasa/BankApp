package com.sbarrasa.bank.service;

import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.bank.product.ProductEntity;
import com.sbarrasa.util.matcher.MatchType;
import com.sbarrasa.util.matcher.ObjectMatcher;
import org.springframework.stereotype.Service;


@Service
public class ProductsMatcher extends ObjectMatcher<ProductDTO> {
  public Boolean match(ProductEntity product, ProductDTO sampleProduct, MatchType matchType) {
    var productDTO = new ProductAdapter().toDTO(product);
    return match(productDTO, sampleProduct, matchType);
  }

  public ProductsMatcher(){
    super(
      ProductDTO::getProductType,
      ProductDTO::getBranch,
      ProductDTO::getTier,
      ProductDTO::getCbu,
      ProductDTO::getCurrency,
      ProductDTO::getIsCredit
    );
  }



}
