package com.sbarrasa.bank.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@JsonInclude(Include.NON_NULL)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 22)
  private String cbu;

  @Column(length = 3)
  private Currency currency;

  @Column(length = 4)
  private Branch branch;

  @Column(length = 10)
  private String tier;

  private Double creditLimit;

  @Column(length = 2)
  private ProductType productType;

  @Transient
  private Boolean isCredit;


  public Boolean getIsCredit(){
    return isCredit !=null
      ? isCredit
      : creditLimit != null;
  }

  public String getName() {
    return productType.getName();
  }

  public boolean match(Product sample){
    if(sample == null) return false;

    if(match(this.productType, sample.productType))
      return false;

    if(match(this.branch, sample.branch))
      return false;

    if(match(this.tier, sample.tier))
      return false;

    if(match(this.cbu, sample.cbu))
      return false;

    if(match(this.currency, sample.currency))
      return false;

    if(match(this.getIsCredit(), sample.isCredit))
      return false;

    return true;
  }

  private boolean match(Object attribute, Object attributeSample){
    return attributeSample != null
      && !attributeSample.equals(attribute);
  }


}
