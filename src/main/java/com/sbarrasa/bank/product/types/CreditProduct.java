package com.sbarrasa.bank.product.types;

import com.sbarrasa.bank.product.ProductEntity;
import jakarta.validation.constraints.NotNull;

public interface CreditProduct {
  ProductEntity setCreditLimit(@NotNull Double creditLimit);
  Double getCreditLimit();
}
