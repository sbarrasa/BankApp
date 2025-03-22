package com.sbarrasa.bank.service;

import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.util.matcher.ObjectMatcher;
import org.springframework.stereotype.Service;


@Service
public class ProductsMatcher extends ObjectMatcher<ProductDTO> {

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
