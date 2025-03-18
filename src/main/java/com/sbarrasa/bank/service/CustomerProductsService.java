package com.sbarrasa.bank.service;

import com.sbarrasa.bank.customer.CustomerEntity;
import com.sbarrasa.bank.product.Product;
import com.sbarrasa.bank.util.matcher.MatchType;
import org.springframework.stereotype.Service;

import java.util.Set;
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

  public void delete(CustomerEntity customer, Product productSample) {
    customer.getProducts()
      .removeIf(product -> product.match(productSample, MatchType.ALL));

  }

  public void add(CustomerEntity customer, Product newProduct) {

    var products = customer.getProducts();

    if(exist(customer, newProduct))
      throw new DuplicatedProductException(customer.getId(), newProduct);

    products.add(newProduct);
  }


}
