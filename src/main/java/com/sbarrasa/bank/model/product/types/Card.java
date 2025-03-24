package com.sbarrasa.bank.model.product.types;

import com.sbarrasa.bank.model.product.Branch;
import com.sbarrasa.bank.model.product.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
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
  @NotNull
  private Branch branch;

  @Override
  public String getDescription() {
    return super.getDescription()+ " " + getBranch();
  }

}
