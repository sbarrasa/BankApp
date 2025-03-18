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
  @PostMapping("/{customerId}/products")
  public Product addProduct(@PathVariable Integer customerId,
                                           @RequestBody Product newProduct){

    var customer = customerService.get(customerId);

    customerProductsService.add(customer, newProduct);

    customerService.update(customer);

    return customerProductsService.find(customer, newProduct);

  }

  @Transactional
  @PutMapping("/{customerId}/products")
  public Collection<Product> updateProducts(@PathVariable Integer customerId,
                                           @RequestBody ProductUpdatePair productUpdatePair){

    var customer = customerService.get(customerId);


    /*actualiza todos los productos que cumplan con el criterio especificado.
    Ver Product.assign para comprender la lógica de actualización de cada producto
     */
    customerProductsService.filter(customer, productUpdatePair.searchProduct())
        .forEach(product -> product.assign(productUpdatePair.updateProduct()));

    customerService.update(customer);

    return customerProductsService.filter(customer, productUpdatePair.searchProduct());

  }

  @Transactional
  @DeleteMapping("/{customerId}/products")
  public Collection<Product> deleteProducts(@PathVariable Integer customerId,
                                           @RequestBody Product searchProduct) {
    var customer = customerService.get(customerId);

    var deletedProducts = customerProductsService.delete(customer, searchProduct);

    customerService.update(customer);
    return deletedProducts;
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

    return customerProductsService.filter(customer,
                                         productSample);
  }




}
