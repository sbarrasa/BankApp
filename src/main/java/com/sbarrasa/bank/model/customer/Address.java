package com.sbarrasa.bank.model.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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


  @JsonIgnore
  public List<String> asList(){
    return Arrays.asList(addressLine, location, city, region, "(" + postalCode + ")", country);
  }

  @JsonIgnore
  public String getFullAddress() {
    return
      asList().stream()
      .filter(Objects::nonNull)
      .collect(Collectors.joining(", "));
  }
}