package com.sbarrasa.bank.service;

import com.sbarrasa.bank.customer.CustomerEntity;
import com.sbarrasa.bank.product.Product;
import com.sbarrasa.bank.product.ProductEntity;
import com.sbarrasa.util.matcher.MatchType;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class CustomerProductsService {


  public Set<Product> filter(CustomerEntity customer, Product newProduct) {
    return customer.getProducts().stream()
      .filter(product -> product.match(newProduct, MatchType.ALL))
      .collect(Collectors.toSet());
  }

  public boolean exist(CustomerEntity customer, Product newProduct) {
    return !filter(customer, newProduct).isEmpty();
  }

  public Set<Product> delete(CustomerEntity customer, Product productSample) {
    var productsFound = new HashSet<>(filter(customer, productSample));

    customer.getProducts()
      .removeIf(product -> product.match(productSample, MatchType.ALL));

    return productsFound;
  }

  public void add(CustomerEntity customer, Product newProduct) {

    var products = customer.getProducts();

    if(exist(customer, newProduct))
      throw new DuplicatedProductException(customer.getId(), newProduct);

    products.add(new ProductEntity(newProduct));
  }


  public Product find(CustomerEntity customer, Product searchProduct) {
    return filter(customer, searchProduct).stream()
      .findFirst()
      .orElseThrow(() -> new ProductNotFondException(customer.getId(), searchProduct));
  }


 
}
