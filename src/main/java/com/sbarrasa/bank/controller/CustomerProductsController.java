package com.sbarrasa.bank.controller;

import com.sbarrasa.bank.util.matcher.MatchType;
import com.sbarrasa.bank.product.Product;
import com.sbarrasa.bank.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/customers")
public class CustomerProductsController {


  private final CustomerService customerService;

  @Autowired
  public CustomerProductsController(CustomerService customerService) {this.customerService = customerService;}

  @Transactional
  @PostMapping("/{customerId}/products")
  public Collection<Product> addProduct(@PathVariable Integer customerId,
                                        @RequestBody Product product){
    var customer = customerService.get(customerId);

    var products = customer.getProducts();

    products.add(product);

    customerService.update(customer);
    return products;

  }

  @Transactional
  @DeleteMapping("/{customerId}/products")
  public Collection<Product> deleteProduct(@PathVariable Integer customerId,
                                           @RequestBody Product productSample) {
    var customer = customerService.get(customerId);

    customer.getProducts()
      .removeIf(product -> product.match(productSample, MatchType.ALL));

    customerService.update(customer);
    return customer.getProducts();
  }


  @GetMapping("/{customerId}/products")
  public Collection<Product> getProducts(@PathVariable Integer customerId) {

    var customer =  customerService.get(customerId);
    return customer.getProducts();

  }

  @GetMapping("/{customerId}/products/filter")
  public Collection<Product> getProductsByExample(@PathVariable Integer customerId,
                                          @RequestBody Product productSample) {

    var customer = customerService.get(customerId);

    return customer.getProducts().stream()
      .filter(product -> product.match(productSample, MatchType.ALL))
      .toList();
  }




}
