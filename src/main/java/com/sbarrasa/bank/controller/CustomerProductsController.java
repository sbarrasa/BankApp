package com.sbarrasa.bank.controller;

import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.bank.controller.dto.ProductUpdatePair;
import com.sbarrasa.bank.service.CustomerProductsService;
import com.sbarrasa.bank.service.CustomerService;
import com.sbarrasa.bank.service.ProductAdapter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/customers")
public class CustomerProductsController {

  private final CustomerService customerService;
  private final CustomerProductsService customerProductsService;
  private final ProductAdapter productAdapter;

  @Autowired
  public CustomerProductsController(CustomerService customerService,
                                    CustomerProductsService customerProductsService,
                                    ProductAdapter productAdapter) {
    this.customerService = customerService;
    this.customerProductsService = customerProductsService;
    this.productAdapter = productAdapter;
  }

  @Transactional
  @PostMapping("/{customerId}/products")
  public ProductDTO addProduct(@PathVariable Integer customerId,
                               @RequestBody ProductDTO newProduct) {

    var customer = customerService.findCustomer(customerId);

    var productAdded = customerProductsService.add(customer, newProduct);

    customerService.save(customer);

    return productAdded;

  }

  @Transactional
  @PutMapping("/{customerId}/products")
  public Set<ProductDTO> updateProducts(@PathVariable Integer customerId,
                                        @RequestBody ProductUpdatePair productUpdatePair) {

    var customer = customerService.findCustomer(customerId);

    var updatedProducts = customerProductsService.update(customer,
      productUpdatePair.searchProduct(),
      productUpdatePair.updateProduct());

    customerService.save(customer);

    return updatedProducts;

  }

  @Transactional
  @DeleteMapping("/{customerId}/products")
  public Set<ProductDTO> deleteProducts(@PathVariable Integer customerId,
                                        @RequestBody ProductDTO searchProduct) {
    var customer = customerService.findCustomer(customerId);

    var deletedProducts = customerProductsService.delete(customer, searchProduct);

    customerService.save(customer);
    return deletedProducts;
  }


  @GetMapping("/{customerId}/products")
  public Set<ProductDTO> getProducts(@PathVariable Integer customerId) {
    var customer = customerService.findCustomer(customerId);

    return productAdapter.toDTOSet(customer.getProducts());
  }

  @GetMapping("/{customerId}/products/filter")
  public Set<ProductDTO> getProductsByExample(@PathVariable Integer customerId,
                                              @RequestBody ProductDTO productSample) {
    var customer = customerService.findCustomer(customerId);

    return customerProductsService.filter(customer, productSample);

  }


}
