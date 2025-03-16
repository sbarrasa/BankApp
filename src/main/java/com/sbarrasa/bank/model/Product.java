package com.sbarrasa.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
  @Id
  private Integer id;

  private String name;

  private String description;

}
