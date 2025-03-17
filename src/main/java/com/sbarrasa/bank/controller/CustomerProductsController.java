package com.sbarrasa.bank.controller;

import com.sbarrasa.bank.product.Product;
import com.sbarrasa.bank.service.CustomerService;
import com.sbarrasa.bank.service.DuplicatedProductException;
import com.sbarrasa.bank.product.ProductsMatcher;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/customers")
public class CustomerProductsController {


  @Autowired
  private CustomerService customerService;

  @Transactional
  @PostMapping("/{customerId}/products")
  public Collection<Product> addProduct(@PathVariable Integer customerId,
                                        @RequestBody Product product){
    var customer = customerService.get(customerId);

    var products = customer.getProducts();

    var productMatcher = new ProductsMatcher(products);

    if(productMatcher.exists(product))
      throw new DuplicatedProductException(customerId, product);

    products.add(product);

    customerService.update(customer);
    return products;

  }

  @Transactional
  @DeleteMapping("/{customerId}/products")
  public Collection<Product> deleteProduct(@PathVariable Integer customerId,
                                           @RequestBody Product productSample) {
    var customer = customerService.get(customerId);

    var productMatcher = new ProductsMatcher(customer.getProducts());

    var noDeletedProducts = productMatcher.except(productSample);

    customer.setProducts(noDeletedProducts);

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
    var productMatcher = new ProductsMatcher(customer.getProducts());

    return productMatcher.filter(productSample);
  }


}
