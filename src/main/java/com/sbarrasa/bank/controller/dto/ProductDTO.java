package com.sbarrasa.bank.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sbarrasa.bank.model.product.Branch;
import com.sbarrasa.bank.model.product.Currency;
import com.sbarrasa.bank.model.product.ProductType;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO  {
  @NotNull
  private ProductType productType;
  private String cbu;
  private Currency currency;
  private Branch branch;
  private String tier;
  private Boolean isCredit;
  private Double creditLimit;
  private String description;
}
