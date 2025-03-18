package com.sbarrasa.bank.service;

import com.sbarrasa.bank.customer.CustomerEntity;
import com.sbarrasa.bank.product.Product;
import com.sbarrasa.bank.util.matcher.MatchType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CustomerProductsService {

  public List<Product> filter(Set<Product> products, Product newProduct) {
    return products.stream()
      .filter(product -> product.match(newProduct, MatchType.ALL))
      .toList();
  }

  public boolean exist(Set<Product> products, Product newProduct) {
    return !filter(products, newProduct).isEmpty();
  }

  public void delete(Set<Product> products, Product productSample) {
    products
      .removeIf(product -> product.match(productSample, MatchType.ALL));

  }

  public void add(CustomerEntity customer, Product newProduct) {

    var products = customer.getProducts();

    if(exist(products, newProduct))
      throw new DuplicatedProductException(customer.getId(), newProduct);

    products.add(newProduct);
  }
}
