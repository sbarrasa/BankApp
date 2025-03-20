package com.sbarrasa.bank.controller;

import com.sbarrasa.bank.customer.Gender;
import com.sbarrasa.bank.util.identifiable.EnumDescribableSet;
import com.sbarrasa.bank.product.Branch;
import com.sbarrasa.bank.product.Currency;
import com.sbarrasa.bank.product.ProductType;
import com.sbarrasa.bank.util.identifiable.IdDescribable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/codes")
public class CodesController {

  @GetMapping("/product_type")
  public Set<IdDescribable<ProductType>> getProductTypeList() {
    return new EnumDescribableSet<>(ProductType.class).listAll();
  }

  @GetMapping("/gender")
  public Set<IdDescribable<Gender>> getGenderList() {
    return new EnumDescribableSet<>(Gender.class).listAll();
  }

  @GetMapping("/branch")
  public Set<IdDescribable<Branch>> getBranchList() {
    return new EnumDescribableSet<>(Branch.class).listAll();
  }

  @GetMapping("/currency")
  public Set<IdDescribable<Currency>> getCurrencyList() {
    return new EnumDescribableSet<>(Currency.class).listAll();
  }

}
