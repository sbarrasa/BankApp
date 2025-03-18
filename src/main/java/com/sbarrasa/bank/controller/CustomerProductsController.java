package com.sbarrasa.bank.controller;

import com.sbarrasa.bank.service.CustomerProductsService;
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
  private final CustomerProductsService customerProductsService;

  @Autowired
  public CustomerProductsController(CustomerService customerService,
                                    CustomerProductsService customerProductsService) {
    this.customerService = customerService;
    this.customerProductsService = customerProductsService;
  }

  @Transactional
  @PostMapping("/{customerId}/product")
  public Collection<Product> addProduct(@PathVariable Integer customerId,
                                           @RequestBody Product newProduct){

    var customer = customerService.get(customerId);

    customerProductsService.add(customer, newProduct);

    customerService.update(customer);
    return customer.getProducts();

  }

  @Transactional
  @DeleteMapping("/{customerId}/product")
  public Collection<Product> deleteProduct(@PathVariable Integer customerId,
                                           @RequestBody Product productSample) {
    var customer = customerService.get(customerId);

    customerProductsService.delete(customer.getProducts(), productSample);

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

    return customerProductsService.filter(customer.getProducts(),
                                         productSample);
  }




}
