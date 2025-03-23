package com.sbarrasa.bank.model.product;


import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.util.id.Desc;
import com.sbarrasa.util.validator.Validator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.function.Consumer;

@Accessors(chain = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type")
public abstract class ProductEntity implements Desc {
  @Transient
  private final Validator validator = new Validator();

  public ProductEntity(){
    productType = defaultProductType();
    validator.addNonNull(this::getProductType, "productType");
  }

  protected Validator getValidator(){
    return validator;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter @Setter
  private Long id;

  @Column(name = "product_type", length = 2, insertable=false, updatable=false)
  @Enumerated(EnumType.STRING)
  @Getter @Setter
  private ProductType productType;


  public ProductEntity assign(ProductDTO productDTO) {
    assignNotNull(productDTO::setProductType, productDTO.getProductType());
    validator.validate();
    return this;
  }

  protected  <T> void assignNotNull(Consumer<T> setter, T value) {
    if (value != null)
      setter.accept(value);
  }

  @Override
  public String getDescription() {
    return productType.getDescription();
  }

  protected abstract ProductType defaultProductType();

}
