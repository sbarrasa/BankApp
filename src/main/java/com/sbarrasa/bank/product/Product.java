package com.sbarrasa.bank.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sbarrasa.bank.product.descriptor.ProductInfo;
import com.sbarrasa.util.matcher.MatchType;

import java.util.function.Consumer;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface Product extends ProductInfo {

  ProductType getProductType();
  Product setProductType(ProductType productType) ;

  String getCbu();
  Product setCbu(String cbu);

  Currency getCurrency();
  Product setCurrency(Currency currency);

  Branch getBranch();
  Product setBranch(Branch branch);

  String getTier();
  Product setTier(String tier);

  Boolean getIsCredit();

  Double getCreditLimit();
  Product setCreditLimit(Double creditLimit);

  @Override
  default String getDescription() {
    return getProductType().getDescriptor().getDescription();
  }

  @Override
  default String getLargeDescription() {
    return getProductType().getDescriptor(this).getLargeDescription();
  }

  default boolean match(Product sample, MatchType matchType) {
    return new ProductsMatcher().match(this, sample, matchType);
  }

  default void assign(Product other) {
    assignNotNull(this::setProductType, other.getProductType());
    assignNotNull(this::setBranch, other.getBranch());
    assignNotNull(this::setTier, other.getTier());
    assignNotNull(this::setCurrency, other.getCurrency());
    assignNotNull(this::setCreditLimit, other.getCreditLimit());
    assignNotNull(this::setCbu, other.getCbu());
  }

  private <T> void assignNotNull(Consumer<T> setter, T value) {
    if (value != null) {
      setter.accept(value);
    }
  }

  default void validate() {
     new ProductValidator(this).validate();
  }
}
