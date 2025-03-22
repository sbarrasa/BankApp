package com.sbarrasa.bank.service;

import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.bank.product.ProductEntity;
import com.sbarrasa.bank.product.types.*;
import org.springframework.stereotype.Service;

@Service
public class ProductAdapter {
  public ProductEntity toEntity(ProductDTO productDTO) {
    ProductEntity productEntity = switch (productDTO.getProductType()) {
      case TC -> new CreditCard()
        .setTier(productDTO.getTier())
        .setCreditLimit(productDTO.getCreditLimit())
        .setBranch(productDTO.getBranch());
      case TD -> new DebitCard()
        .setBranch(productDTO.getBranch());
      case CA -> new SavingAccount()
        .setCbu(productDTO.getCbu())
        .setCurrency(productDTO.getCurrency());
      case CC -> new CheckingAccount()
        .setCreditLimit(productDTO.getCreditLimit())
        .setCurrency(productDTO.getCurrency())
        .setCbu(productDTO.getCbu());
    };

    productEntity
      .setProductType(productDTO.getProductType());

    return productEntity;


  }

  public ProductDTO toDTO(ProductEntity productEntity) {
    ProductDTO dto = new ProductDTO()
      .setProductType(productEntity.getProductType())
      .setDescription(productEntity.getDescription());

    if (productEntity instanceof Account acc) {
      dto.setCbu(acc.getCbu())
        .setCurrency(acc.getCurrency());
    }
    if (productEntity instanceof Card card) {
      dto.setBranch(card.getBranch());
    }
    if (productEntity instanceof CreditCard tc) {
      dto.setCreditLimit(tc.getCreditLimit())
        .setTier(tc.getTier());
    }
    if (productEntity instanceof CreditProduct creditProduct) {
      dto.setCreditLimit(creditProduct.getCreditLimit());
      dto.setIsCredit(true);
    } else {
      dto.setIsCredit(false);
    }

    return dto;
  }
}
