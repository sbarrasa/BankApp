package com.sbarrasa.bank.model.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
  @NotNull
  private String addressLine;

  @Column(length = 30)
  private String location;

  @NotNull
  @Column(length = 30)
  private String city;

  @NotNull
  @Column(length = 30)
  private String region;

  @NotNull
  @Column(length = 10)
  private String postalCode;

  @NotNull
  @Column(length = 30)
  private String country;
}