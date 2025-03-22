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

  public CreditCard(){
    super();
    this.setProductType(ProductType.TC);
  }

  private String getTierIfNotNull() {
    return tier == null
      ? ""
      : " " +tier;

  }

  @Override
  public CreditCard assign(ProductDTO other){
    super.assign(other);
    assignNotNull(this::setCreditLimit, other.getCreditLimit());
    assignNotNull(this::setTier, other.getTier());

    return this;
  }

}
