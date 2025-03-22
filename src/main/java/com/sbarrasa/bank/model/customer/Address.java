package com.sbarrasa.bank.model.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
  private String addressLine;
  @Column(length = 30)
  private String city;
  @Column(length = 30)
  private String region;
  @Column(length = 10)
  private String postalCode;
  @Column(length = 30)
  private String country;
}