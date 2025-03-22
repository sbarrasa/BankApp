package com.sbarrasa.bank.service;

import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.bank.repository.CustomerEntity;
import com.sbarrasa.bank.product.ProductEntity;
import com.sbarrasa.util.matcher.MatchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class CustomerProductsService {
  private final ProductAdapter productAdapter;
  private final ProductsMatcher productsMatcher;

  @Autowired
  public CustomerProductsService(ProductAdapter productAdapter, ProductsMatcher productsMatcher) {
    this.productAdapter = productAdapter;
    this.productsMatcher = productsMatcher;
  }


  public Set<ProductDTO> filter(CustomerEntity customer, ProductDTO newProduct) {
    return customer.getProducts().stream()
      .filter(product -> productsMatcher.match(product, newProduct, MatchType.ALL))
      .collect(Collectors.toSet());
  }

  public boolean exist(CustomerEntity customer, ProductDTO newProduct) {
    return !filter(customer, newProduct).isEmpty();
  }

  public Set<ProductDTO> delete(CustomerEntity customer, ProductDTO productSample) {
    var productsFound = new HashSet<>(filter(customer, productSample));

    customer.getProducts()
      .removeIf(product -> product.match(productSample, MatchType.ALL));

    return productsFound;
  }

  public void add(CustomerEntity customer, Product newProduct) {
    newProduct.validate();

    var products = customer.getProducts();

    if(exist(customer, newProduct))
      throw new DuplicatedProductException(customer.getId(), newProduct);

    var productEntity = new ProductEntity(newProduct);

    products.add(productEntity);
  }


  public Product find(CustomerEntity customer, Product searchProduct) {
    return filter(customer, searchProduct).stream()
      .findFirst()
      .orElseThrow(() -> new ProductNotFondException(customer.getId(), searchProduct));
  }


 
}
