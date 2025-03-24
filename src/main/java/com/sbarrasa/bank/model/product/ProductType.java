package com.sbarrasa.bank.model.product;

import com.sbarrasa.bank.model.product.types.CheckingAccount;
import com.sbarrasa.bank.model.product.types.CreditCard;
import com.sbarrasa.bank.model.product.types.DebitCard;
import com.sbarrasa.bank.model.product.types.SavingAccount;
import com.sbarrasa.util.id.Desc;

import java.util.function.Supplier;

public enum ProductType implements Desc {
  TC(CreditCard::new) ,
  TD(DebitCard::new),
  CC(CheckingAccount::new),
  CA(SavingAccount::new);

  private final Supplier<? extends ProductEntity> productSupplier;
  private String description;

  ProductType(Supplier<? extends ProductEntity> productSupplier) {
    this.productSupplier = productSupplier;
  }

  @Override
  public String getDescription() {
    if(description == null) {
      description = productSupplier.get().getProductDescription();
    }
    return description;
  }

  @SuppressWarnings("unchecked")
  public <T extends ProductEntity> T createProduct() {
    T product = (T) productSupplier.get();
    product.setProductType(this);
    return product;
  }
}
