package com.sbarrasa.bank.product;


import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
public class ProductEntity implements Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 2)
  @Enumerated(EnumType.STRING)
  private ProductType productType;

  @Column(length = 22)
  private String cbu;

  @Column(length = 3)
  @Enumerated(EnumType.STRING)
  private Currency currency;

  @Column(length = 4)
  @Enumerated(EnumType.STRING)
  private Branch branch;

  @Column(length = 10)
  private String tier;

  private Double creditLimit;


  public ProductEntity(Product product) {
    assign(product);
  }

  public ProductEntity() {

  }

  public Boolean getIsCredit(){
    return creditLimit != null;
  }
}
