package com.sbarrasa.bank.service;

import com.sbarrasa.bank.customer.CustomerEntity;
import com.sbarrasa.bank.product.Product;
import com.sbarrasa.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {
  @Autowired
  private CustomerRepository customerRepository;

  public Set<Product> except(Set<Product> products, Product productSample){
    return filter(products, productSample, false);
  }

  public Set<Product> filter(Set<Product> products, Product productSample){
    return filter(products, productSample, true);
  }

  public Set<Product> filter(Set<Product> products, Product productSample, boolean matchStatus){
    return products.stream()
      .filter(product -> product.match(productSample) == matchStatus)
      .collect(Collectors.toSet());
  }


  public CustomerEntity getCustomer(Integer customerId) {
    return  customerRepository.findById(customerId)
      .orElseThrow(() -> new CustomerNotFoundException(customerId));

  }

  public Set<Product> getProducts(Integer customerId){
    return getCustomer(customerId).getProducts();
  }
}
