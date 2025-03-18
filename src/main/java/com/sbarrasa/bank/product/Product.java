package com.sbarrasa.bank.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.*;

import com.sbarrasa.bank.util.descriptible.Descriptible;
import com.sbarrasa.bank.util.matcher.MatchType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@JsonInclude(Include.NON_NULL)
public class Product implements Descriptible {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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


  @Column(length = 2)
  @Enumerated(EnumType.STRING)
  private ProductType productType;

  @Transient
  Boolean isCredit;

  public Boolean getIsCredit(){
    if(isCredit == null)
      isCredit = creditLimit != null;

    return isCredit;
  }

  public String getName() {
    return productType.getDescription();
  }

  @Override
  public String getDescription() {
    return new ProductDescriptionBuilder(this).build();
  }

  public boolean match(Product sample, MatchType matchType) {
    return new ProductsMatcher().match(this, sample, matchType);
  }


}
