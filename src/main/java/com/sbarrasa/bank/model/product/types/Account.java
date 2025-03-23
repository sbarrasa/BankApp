package com.sbarrasa.bank.model.product.types;


import com.sbarrasa.bank.model.product.Currency;
import com.sbarrasa.bank.model.product.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
public abstract class Account extends ProductEntity {

  @Column(length = 22)
  @NotNull
  @Size(min = 22, max = 22)
  private String cbu;

  @Column(length = 3)
  @Enumerated(EnumType.STRING)
  @NotNull
  private Currency currency;

  @Override
  public String getDescription() {
    return getProductType().getDescription()+" en "+getCurrency().getDescription();
  }

}
