package com.sbarrasa.bank.product.types;

import com.sbarrasa.bank.controller.dto.ProductDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("CC")
@Data
@Accessors(chain = true)
public class CheckingAccount extends Account implements CreditProduct {
  private Double creditLimit;

  @Override
  public CheckingAccount assign(ProductDTO other){
    super.assign(other);
    assignNotNull(this::setCreditLimit, other.getCreditLimit());

    return this;
  }

}
