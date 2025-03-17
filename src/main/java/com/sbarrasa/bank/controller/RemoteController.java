package com.sbarrasa.bank.controller;

import com.sbarrasa.bank.customer.Customer;
import com.sbarrasa.bank.customer.CustomerDTO;
import com.sbarrasa.bank.customer.CustomerEntity;
import com.sbarrasa.bank.product.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Set;


@Controller
@RequestMapping("/remote/customers")
public class RemoteController {
  private final RestTemplate restTemplate;
  private static final String customersApiUrl = "http://localhost:8080/api/customers/" ;


  public RemoteController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<CustomerEntity> getCustomerWithProducts(@PathVariable Integer customerId){
    var customerUrl = customersApiUrl+customerId;
    Customer customer = restTemplate.getForObject(customerUrl, CustomerDTO.class);

    var productsUrl = customersApiUrl+customerId+"/products";
    var products = restTemplate.getForObject(productsUrl, Product[].class);

    var customerEntity = new CustomerEntity(customer);
    customerEntity.setProducts(Set.copyOf(Arrays.asList(products)));
    return new ResponseEntity<>(customerEntity, HttpStatus.OK);
  }

}
