package com.sbarrasa.bank.controller;

import com.sbarrasa.bank.controller.dto.CustomerDTO;
import com.sbarrasa.bank.controller.dto.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

//Simulación de un controller que usando restTemplate llama a otros microservicios para armar un response
@Controller
@RequestMapping("/remote/customers")
public class RemoteController {
  private final RestTemplate restTemplate;
  private static final String customersApiUrl = "http://localhost:8080/api/customers/" ;


  public RemoteController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<CustomerDTO> getCustomerWithProducts(@PathVariable Integer customerId){
    var customerUrl = customersApiUrl+customerId;
    var customer = restTemplate.getForObject(customerUrl, CustomerDTO.class);
    Objects.requireNonNull(customer);

    var productsUrl = customersApiUrl+customerId+"/products";
    var products = restTemplate.getForObject(productsUrl, ProductDTO[].class);
    Objects.requireNonNull(products);

    customer.setProducts(new HashSet<>(Arrays.asList(products)));
    return new ResponseEntity<>(customer, HttpStatus.OK);
  }

}
