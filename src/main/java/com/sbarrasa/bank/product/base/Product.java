package com.sbarrasa.bank.product.base;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sbarrasa.bank.product.CheckingAccount;
import com.sbarrasa.bank.product.CreditCard;
import com.sbarrasa.bank.product.DebitCard;
import com.sbarrasa.bank.product.SavingAccount;
import jakarta.persistence.*;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "product_type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = CheckingAccount.class, name = "CC"),
  @JsonSubTypes.Type(value = CreditCard.class, name = "TC"),
  @JsonSubTypes.Type(value = DebitCard.class, name = "TD"),
  @JsonSubTypes.Type(value = SavingAccount.class, name = "CA")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Product {
  private String name;

  @Id
  @Column(length = 2, insertable=false, updatable=false)
  public abstract String getProduct_type();  // Esta columna se utiliza para el discriminador

  @Column(length = 30)
  public abstract String getDescription();


}