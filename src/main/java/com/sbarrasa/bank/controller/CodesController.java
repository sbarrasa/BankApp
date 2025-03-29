package com.sbarrasa.bank.controller;

import com.sbarrasa.bank.model.customer.Gender;
import com.sbarrasa.bank.model.product.Branch;
import com.sbarrasa.bank.model.product.Currency;
import com.sbarrasa.bank.service.ProductFactory;
import com.sbarrasa.util.id.EnumIdDesc;
import com.sbarrasa.util.id.IdDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/codes")
public class CodesController {

  private final ProductFactory productFactory;

  @Autowired
  public CodesController(ProductFactory productFactory) {this.productFactory = productFactory;}

  @GetMapping("/product_type")
  public Set<IdDesc<String>> getProductTypeList() {
    return productFactory.getAllDescriptors();
  }

  @GetMapping("/gender")
  public Set<IdDesc<Gender>> getGenderList() {
    return new EnumIdDesc<>(Gender.class).asSet();
  }

  @GetMapping("/branch")
  public Set<IdDesc<Branch>> getBranchList() {
    return new EnumIdDesc<>(Branch.class).asSet();
  }

  @GetMapping("/currency")
  public Set<IdDesc<Currency>> getCurrencyList() {
    return new EnumIdDesc<>(Currency.class).asSet();
  }

}
