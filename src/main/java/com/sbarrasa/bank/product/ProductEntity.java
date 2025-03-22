package com.sbarrasa.bank.product;


import com.sbarrasa.util.id.Desc;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type")
public abstract class ProductEntity implements Desc {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 2)
  @Enumerated(EnumType.STRING)
  private ProductType productType;

}
