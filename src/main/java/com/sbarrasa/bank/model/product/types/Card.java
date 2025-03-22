package com.sbarrasa.bank.model.product.types;


import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.bank.model.product.Branch;
import com.sbarrasa.bank.model.product.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public abstract class Card extends ProductEntity {
  @Column(length = 4)
  @Enumerated(EnumType.STRING)
  @NotNull
  private Branch branch;

  @Override
  public String getDescription() {
    return getProductType().getDescription()+ " " + getBranch();
  }

  @Override
  public Card assign(ProductDTO other){
    super.assign(other);
    assignNotNull(this::setBranch, other.getBranch());
    return this;
  }

}
