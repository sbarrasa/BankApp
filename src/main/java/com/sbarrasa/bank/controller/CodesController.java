package com.sbarrasa.bank.controller;

import com.sbarrasa.bank.customer.Gender;
import com.sbarrasa.bank.util.descriptible.EnumDescriptionSet;
import com.sbarrasa.bank.util.descriptible.EnumDescription;
import com.sbarrasa.bank.product.Branch;
import com.sbarrasa.bank.product.Currency;
import com.sbarrasa.bank.product.ProductType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/codes")
public class CodesController {

  @GetMapping("/product_type")
  public Set<EnumDescription<ProductType>> getProductTypeList() {
    return new EnumDescriptionSet<>(ProductType.class).listAll();
  }

  @GetMapping("/gender")
  public Set<EnumDescription<Gender>> getGenderList() {
    return new EnumDescriptionSet<>(Gender.class).listAll();
  }

  @GetMapping("/branch")
  public Set<EnumDescription<Branch>> getBranchList() {
    return new EnumDescriptionSet<>(Branch.class).listAll();
  }

  @GetMapping("/currency")
  public Set<EnumDescription<Currency>> getCurrencyList() {
    return new EnumDescriptionSet<>(Currency.class).listAll();
  }

}
