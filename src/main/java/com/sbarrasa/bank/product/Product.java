package com.sbarrasa.bank.product;

import com.sbarrasa.util.matcher.MatchType;

import java.util.function.Consumer;

public interface Product  {
  ProductType getProductType();
  String getCbu();
  Currency getCurrency();
  Branch getBranch();
  String getTier();
  Boolean getIsCredit();
  Double getCreditLimit();

  Product setCreditLimit(Double aDouble);
  Product setCurrency(Currency currency);
  Product setTier(String s);
  Product setBranch(Branch branch);
  Product setProductType(ProductType productType);
  Product setCbu(String s);

  default String getName() {
    return getProductType().getDescription();
  }

  default String getDescription() {
    return new ProductDescriptionBuilder(this).build();
  }

  default boolean match(Product sample, MatchType matchType) {
    return new ProductsMatcher().match(this, sample, matchType);
  }

  default void assign(Product other) {
    assignIfNotNull(this::setProductType, other.getProductType());
    assignIfNotNull(this::setBranch, other.getBranch());
    assignIfNotNull(this::setTier, other.getTier());
    assignIfNotNull(this::setCurrency, other.getCurrency());
    assignIfNotNull(this::setCreditLimit, other.getCreditLimit());
    assignIfNotNull(this::setCbu, other.getCbu());
  }

  private <T> void assignIfNotNull(Consumer<T> setter, T value) {
    if (value != null) {
      setter.accept(value);
    }

  }
}
