package com.sbarrasa.bank.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sbarrasa.bank.product.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO implements Product {
  private ProductType productType;
  private String cbu;
  private Currency currency;
  private Branch branch;
  private String tier;
  private Boolean isCredit;
  private Double creditLimit;

}
