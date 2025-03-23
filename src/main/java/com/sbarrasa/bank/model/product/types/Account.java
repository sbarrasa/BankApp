package com.sbarrasa.bank.model.product.types;


import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.bank.model.product.Currency;
import com.sbarrasa.bank.model.product.ProductEntity;
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
  public Account(){
    super();
    getValidator()
      .addNonNull(this::getCbu, "cbu")
      .addNonNull(this::getCurrency, "currency");
  }

  @Column(length = 22)
  private String cbu;

  @Column(length = 3)
  @Enumerated(EnumType.STRING)
  private Currency currency;

  @Override
  public String getDescription() {
    return getProductType().getDescription()+" en "+getCurrency().getDescription();
  }

  @Override
  public Account assign(ProductDTO other){
    assignNotNull(this::setCbu, other.getCbu());
    assignNotNull(this::setCurrency, other.getCurrency());

    super.assign(other);

    return this;
  }



}
