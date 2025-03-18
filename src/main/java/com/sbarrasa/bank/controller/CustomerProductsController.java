package com.sbarrasa.bank.controller;

import com.sbarrasa.bank.service.CustomerProductsService;
import com.sbarrasa.bank.service.DuplicatedProductException;
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
  @PostMapping("/{customerId}/products")
  public Collection<Product> addProduct(@PathVariable Integer customerId,
                                           @RequestBody Product newProduct){

    var customer = customerService.get(customerId);

    var products = customer.getProducts();

    if(customerProductsService.exist(products, newProduct))
      throw new DuplicatedProductException(customerId, newProduct);

    products.add(newProduct);

    customerService.update(customer);
    return products;

  }

  @Transactional
  @DeleteMapping("/{customerId}/products")
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

    var filtered = customerProductsService.filter(customer.getProducts(),
                                                                productSample);

    return filtered;
  }




}
