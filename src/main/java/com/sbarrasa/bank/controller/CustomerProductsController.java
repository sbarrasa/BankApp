package com.sbarrasa.bank.controller;

import com.sbarrasa.bank.product.Product;
import com.sbarrasa.bank.service.CustomerService;
import com.sbarrasa.bank.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/customers")
public class CustomerProductsController {


  @Autowired
  private ProductService productService;

  @Autowired
  private CustomerService customerService;

  @Transactional
  @PostMapping("/{customerId}/products")
  public Collection<Product> addProduct(@PathVariable Integer customerId,
                                        @RequestBody Product product){
    var customer = productService.getCustomer(customerId);

    var products = customer.getProducts();
    products.add(product);

    customerService.update(customer);
    return products;

  }

  @Transactional
  @DeleteMapping("/{customerId}/products")
  public Collection<Product> deleteProduct(@PathVariable Integer customerId,
                                           @RequestBody Product productSample) {
    var customer = productService.getCustomer(customerId);

    customerService.update(customer);
    return customer.getProducts();
  }

  @GetMapping("/{customerId}/products")
  public Collection<Product> showProducts(@PathVariable Integer customerId,
                                          @RequestBody Product productSample) {

    var products = productService.getProducts(customerId);

    return productService.filterByExample(products, productSample);

  }


}
