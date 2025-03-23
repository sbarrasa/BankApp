package com.sbarrasa.bank.model.product.types;


import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.bank.model.product.ProductType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("TC")
@Accessors(chain = true)
public class CreditCard extends Card implements CreditProduct {
  private Double creditLimit;

  @Column(length = 10)
  private String tier;

  @Override
  public String getDescription() {
    return super.getDescription() +getTierIfNotNull();
  }

  @Override
  protected ProductType defaultProductType() {
    return ProductType.TC;
  }

  public CreditCard(){
    super();
    getValidator()
      .addNonNull(this::getCreditLimit , "creditLimit")
      .addCondition(() ->this.getCreditLimit() >0, "creditLimit", "debe ser mayor que 0");
  }

  private String getTierIfNotNull() {
    return tier == null
      ? ""
      : " " +tier;
  }

  @Override
  public CreditCard assign(ProductDTO other){
    assignNotNull(this::setCreditLimit, other.getCreditLimit());
    assignNotNull(this::setTier, other.getTier());
    super.assign(other);
    return this;
  }

}
