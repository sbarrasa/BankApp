package com.sbarrasa.bank.service;

import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.bank.model.customer.CustomerEntity;
import com.sbarrasa.bank.service.exceptions.DuplicatedProductException;
import com.sbarrasa.bank.service.exceptions.ProductNotFondException;
import com.sbarrasa.util.matcher.MatchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerProductsService {
  private final ProductMatcher matcher;
  private final ProductAdapter adapter;

  @Autowired
  public CustomerProductsService(ProductMatcher matcher, ProductAdapter adapter ) {
    this.matcher = matcher;
    this.adapter = adapter;
  }


  public Set<ProductDTO> filter(CustomerEntity customer, ProductDTO newProduct) {
    var filteredProducts = customer.getProducts().stream()
      .filter(product -> matcher.match(product, newProduct, MatchType.ALL))
      .collect(Collectors.toSet());

    return adapter.toDTOSet(filteredProducts);
  }



  public boolean exist(CustomerEntity customer, ProductDTO newProduct) {
    return !filter(customer, newProduct).isEmpty();
  }

  public Set<ProductDTO> update(CustomerEntity customer, ProductDTO productSample, ProductDTO updateProduct) {
    if(filter(customer, productSample).isEmpty())
      throw new ProductNotFondException(customer.getId(), productSample);

    var updatedProducts = customer.getProducts().stream()
      .filter(currentProduct -> matcher.match(currentProduct, productSample, MatchType.ALL))
      .map(currentProduct -> currentProduct.assign(updateProduct))
      .collect(Collectors.toSet());

    return adapter.toDTOSet(updatedProducts);
  }

  public Set<ProductDTO> delete(CustomerEntity customer, ProductDTO productSample) {
    var productsToDelete = filter(customer, productSample);
    if(productsToDelete.isEmpty())
      throw new ProductNotFondException(customer.getId(), productSample);

    customer.getProducts()
      .removeIf(product ->
        matcher.match(product, productSample, MatchType.ALL)
      );

    return productsToDelete;
  }

  public ProductDTO add(CustomerEntity customer, ProductDTO newProduct) {

    var products = customer.getProducts();

    if(exist(customer, newProduct))
      throw new DuplicatedProductException(customer.getId(), newProduct);

    var productEntity = adapter.toEntity(newProduct);

    products.add(productEntity);

    return adapter.toDTO(productEntity);
  }




}
