package com.sbarrasa.bank.product.types;

import com.sbarrasa.bank.product.ProductEntity;

public interface CreditProduct {
  ProductEntity setCreditLimit(Double creditLimit);
  Double getCreditLimit();
}
