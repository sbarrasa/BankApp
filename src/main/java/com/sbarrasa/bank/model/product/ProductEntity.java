package com.sbarrasa.bank.model.product;


import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.util.id.Desc;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.function.Consumer;


@Getter
@Setter
@Accessors(chain = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type")
public abstract class ProductEntity implements Desc {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_type", length = 2, insertable=false, updatable=false)
  @Enumerated(EnumType.STRING)
  private ProductType productType;

  public ProductEntity assign(ProductDTO productDTO) {
    assignNotNull(productDTO::setProductType, productDTO.getProductType());
    return this;
  }

  protected  <T> void assignNotNull(Consumer<T> setter, T value) {
    if (value != null)
      setter.accept(value);
  }

  @Override
  public String getDescription() {
    return productType != null
      ? productType.getDescription()
      : null;
  }

}
