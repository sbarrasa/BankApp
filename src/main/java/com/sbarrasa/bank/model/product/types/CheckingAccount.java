package com.sbarrasa.bank.model.product.types;

import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.bank.model.product.ProductType;
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

  public CheckingAccount(){
    super();
    getValidator()
      .addNonNull(this::getCreditLimit, "creditLimit")
      .addCondition(() ->this.getCreditLimit() >0, "creditLimit", "debe ser mayor que 0");
  }

  @Override
  public CheckingAccount assign(ProductDTO other){
    assignNotNull(this::setCreditLimit, other.getCreditLimit());
    super.assign(other);

    return this;
  }

  @Override
  protected ProductType defaultProductType() {
    return ProductType.CC;
  }

}
