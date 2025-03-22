package com.sbarrasa.bank.product.types;


import com.sbarrasa.bank.product.Branch;
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
public abstract class Card extends ProductEntity {
  @Column(length = 4)
  @Enumerated(EnumType.STRING)
  private Branch branch;

  @Override
  public String getDescription() {
    return getProductType().getDescription()+ " " + getBranch();
  }


}
