package com.sbarrasa.bank.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.*;

import com.sbarrasa.bank.util.descriptible.Descriptible;
import com.sbarrasa.bank.util.matcher.MatchType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.function.Consumer;

@Data
@Accessors(chain = true)
@Entity
@JsonInclude(Include.NON_NULL)
public class Product implements Descriptible {

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

  public Product assign(Product other) {
    assignIfNotNull(this::setProductType, other.getProductType());
    assignIfNotNull(this::setBranch, other.getBranch());
    assignIfNotNull(this::setTier, other.getTier());
    assignIfNotNull(this::setCurrency, other.getCurrency());
    assignIfNotNull(this::setCreditLimit, other.getCreditLimit());
    assignIfNotNull(this::setCbu, other.getCbu());

    return this;
  }

  private <T> void assignIfNotNull(Consumer<T> setter, T value) {
    if (value != null) {
      setter.accept(value);
    }

  }
}
