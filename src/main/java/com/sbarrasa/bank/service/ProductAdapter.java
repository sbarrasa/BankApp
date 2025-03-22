package com.sbarrasa.bank.service;

import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.bank.model.product.ProductEntity;
import com.sbarrasa.bank.model.product.types.*;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductAdapter {
  public Set<ProductEntity> toEntitySet(Set<ProductDTO> productDTOSet) {
    return productDTOSet.stream()
      .map(this::toEntity)
      .collect(Collectors.toSet());
  }

  public Set<ProductDTO> toDTOSet(Set<ProductEntity> productEntitieSet) {
    return productEntitieSet.stream()
      .map(this::toDTO)
      .collect(Collectors.toSet());
  }

  public ProductEntity toEntity(ProductDTO productDTO) {
    ProductEntity productEntity = switch (productDTO.getProductType()) {
      case TC -> new CreditCard();
      case TD -> new DebitCard();
      case CA -> new SavingAccount();
      case CC -> new CheckingAccount();
    };

    productEntity.assign(productDTO);

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
      dto.setTier(tc.getTier());
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
