package com.sbarrasa.bank.model.product;

import com.sbarrasa.bank.model.product.types.CheckingAccount;
import com.sbarrasa.bank.model.product.types.CreditCard;
import com.sbarrasa.bank.model.product.types.DebitCard;
import com.sbarrasa.bank.model.product.types.SavingAccount;
import com.sbarrasa.util.id.Desc;
import lombok.Getter;

import java.util.function.Supplier;

public enum ProductType implements Desc {
  TC(CreditCard::new, CreditCard.NAME) ,
  TD(DebitCard::new, DebitCard.NAME),
  CC(CheckingAccount::new, CheckingAccount.NAME),
  CA(SavingAccount::new, SavingAccount.NAME);

  private final Supplier<? extends ProductEntity> productSupplier;

  @Getter
  private final String description;

  ProductType(Supplier<? extends ProductEntity> productSupplier, String name) {
    this.productSupplier = productSupplier;
    this.description = name;
  }

  @SuppressWarnings("unchecked")
  public <T extends ProductEntity> T createProduct() {
    return (T) productSupplier.get();
  }
}
