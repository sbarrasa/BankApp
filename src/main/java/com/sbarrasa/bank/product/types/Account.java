package com.sbarrasa.bank.product.types;


import com.sbarrasa.bank.product.Currency;
import com.sbarrasa.bank.product.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
public abstract class Account extends ProductEntity {
  @Column(length = 22)
  private String cbu;

  @Column(length = 3)
  @Enumerated(EnumType.STRING)
  private Currency currency;

  @Override
  public String getDescription() {
    return getProductType().getDescription()+" en "+getCurrency().getDescription();
  }


}
