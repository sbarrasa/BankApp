package com.sbarrasa.bank.service;

import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.bank.repository.CustomerEntity;
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

  public Set<ProductDTO> delete(CustomerEntity customer, ProductDTO productSample) {
    var products = customer.getProducts();

    products
      .removeIf(product ->
        matcher.match(product, productSample, MatchType.ALL)
      );

    return adapter.toDTOSet(products);
  }

  public void add(CustomerEntity customer, ProductDTO newProduct) {

    var products = customer.getProducts();

    if(exist(customer, newProduct))
      throw new DuplicatedProductException(customer.getId(), newProduct);

    var productEntity = adapter.toEntity(newProduct);

    products.add(productEntity);
  }


  public ProductDTO find(CustomerEntity customer, ProductDTO searchProduct) {
    return filter(customer, searchProduct).stream()
      .findFirst()
      .orElseThrow(() -> new ProductNotFondException(customer.getId(), searchProduct));
  }


  public void update(CustomerEntity customer, ProductDTO newProduct) {
      customer.getProducts().forEach(currentProduct ->{
        if(matcher.match(currentProduct, newProduct, MatchType.ALL))
          currentProduct.assign(newProduct);
      });
  }
}
