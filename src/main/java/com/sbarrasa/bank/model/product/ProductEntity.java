package com.sbarrasa.bank.model.product;


import com.sbarrasa.bank.model.product.types.CreditProduct;
import com.sbarrasa.util.id.Desc;
import com.sbarrasa.util.validator.Validator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type")
@Table(name = "products")
@Data
public abstract class ProductEntity implements Desc {

  @Transient
  private final Validator validator = new Validator();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_type", length = 2, insertable=false, updatable=false)
  @Enumerated(EnumType.STRING)
  @NotNull
  public abstract String getProductType();

  @Override
  public String getDescription() {
    return getName();
  }

  public boolean getIsCredit(){
    return this instanceof CreditProduct;
  }

  public abstract String getName();
}
