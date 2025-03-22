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
                                    CustomerProductsService customerProductsService, ProductAdapter productAdapter, ProductAdapter productAdapter1) {
    this.customerService = customerService;
    this.customerProductsService = customerProductsService;
    this.productAdapter = productAdapter1;
  }

  @Transactional
  @PostMapping("/{customerId}/products")
  public ProductDTO addProduct(@PathVariable Integer customerId,
                            @RequestBody ProductDTO newProduct){

    var customer = customerService.get(customerId);

    customerProductsService.add(customer, newProduct);

    customerService.update(customer);

    return customerProductsService.find(customer, newProduct);

  }

  @Transactional
  @PutMapping("/{customerId}/products")
  public Set<ProductDTO> updateProducts(@PathVariable Integer customerId,
                                                  @RequestBody ProductUpdatePair productUpdatePair){

    var customer = customerService.get(customerId);


    /*actualiza todos los productos que cumplan con el criterio especificado.
    Ver Product.assign para comprender la lógica de actualización de cada producto
     */
    customerProductsService.update(customer, productUpdatePair.searchProduct());

    customerService.update(customer);

    return customerProductsService.filter(customer, productUpdatePair.searchProduct());

  }

  @Transactional
  @DeleteMapping("/{customerId}/products")
  public Set<ProductDTO> deleteProducts(@PathVariable Integer customerId,
                                                  @RequestBody ProductDTO searchProduct) {
    var customer = customerService.get(customerId);

    var deletedProducts = customerProductsService.delete(customer, searchProduct);

    customerService.update(customer);
    return deletedProducts;
  }


  @GetMapping("/{customerId}/products")
  public Set<ProductDTO> getProducts(@PathVariable Integer customerId) {
    var customer =  customerService.get(customerId);

    return productAdapter.toDTOSet(customer.getProducts());
  }

  @GetMapping("/{customerId}/products/filter")
  public Set<ProductDTO> getProductsByExample(@PathVariable Integer customerId,
                                                        @RequestBody ProductDTO productSample) {
    var customer = customerService.get(customerId);

    return customerProductsService.filter(customer, productSample);

  }


}
