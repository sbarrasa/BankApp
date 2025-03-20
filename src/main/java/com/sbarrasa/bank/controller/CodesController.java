package com.sbarrasa.bank.controller;

import com.sbarrasa.bank.customer.Gender;
import com.sbarrasa.util.id.EnumDesc;
import com.sbarrasa.bank.product.Branch;
import com.sbarrasa.bank.product.Currency;
import com.sbarrasa.bank.product.ProductType;
import com.sbarrasa.util.id.IdDesc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/codes")
public class CodesController {

  @GetMapping("/product_type")
  public Set<IdDesc<ProductType>> getProductTypeList() {
    return new EnumDesc<>(ProductType.class).asSet();
  }

  @GetMapping("/gender")
  public Set<IdDesc<Gender>> getGenderList() {
    return new EnumDesc<>(Gender.class).asSet();
  }

  @GetMapping("/branch")
  public Set<IdDesc<Branch>> getBranchList() {
    return new EnumDesc<>(Branch.class).asSet();
  }

  @GetMapping("/currency")
  public Set<IdDesc<Currency>> getCurrencyList() {
    return new EnumDesc<>(Currency.class).asSet();
  }

}
