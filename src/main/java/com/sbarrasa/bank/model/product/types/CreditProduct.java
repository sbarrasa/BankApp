package com.sbarrasa.bank.model.product.types;

import com.sbarrasa.bank.model.product.ProductEntity;
import jakarta.validation.constraints.NotNull;

public interface CreditProduct {
  ProductEntity setCreditLimit(@NotNull Double creditLimit);

  Double getCreditLimit();
}
