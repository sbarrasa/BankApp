package com.sbarrasa.bank.controller;

import com.sbarrasa.bank.customer.Gender;
import com.sbarrasa.bank.descriptible.EnumDescriptibleSet;
import com.sbarrasa.bank.descriptible.EnumDescriptibleDTO;
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
  public Set<EnumDescriptibleDTO<ProductType>> getProductTypeList() {
    return new EnumDescriptibleSet<>(ProductType.class).listAll();
  }

  @GetMapping("/gender")
  public Set<EnumDescriptibleDTO<Gender>> getGenderList() {
    return new EnumDescriptibleSet<>(Gender.class).listAll();
  }

  @GetMapping("/branch")
  public Set<EnumDescriptibleDTO<Branch>> getBranchList() {
    return new EnumDescriptibleSet<>(Branch.class).listAll();
  }

  @GetMapping("/currency")
  public Set<EnumDescriptibleDTO<Currency>> getCurrencyList() {
    return new EnumDescriptibleSet<>(Currency.class).listAll();
  }

}
