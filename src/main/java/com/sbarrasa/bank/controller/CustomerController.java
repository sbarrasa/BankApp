package com.sbarrasa.bank.controller;

import com.sbarrasa.bank.customer.Customer;
import com.sbarrasa.bank.customer.CustomerDTO;
import com.sbarrasa.bank.customer.CustomerEntity;
import com.sbarrasa.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  private final CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public ResponseEntity<List<Customer>> getAll() {
    var customers = customerService.getAll();
    return customers.isEmpty()
      ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
      : new ResponseEntity<>(customers, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public Customer get(@PathVariable Integer id) {
    return new CustomerDTO(customerService.get(id));
  }

  @PostMapping
  public ResponseEntity<Customer> create(@RequestBody CustomerEntity customer) {
    var createdCustomer = customerService.create(customer);
    return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public Customer update(@RequestBody CustomerEntity customer) {
    return customerService.update(customer);
  }

  @DeleteMapping("/{id}")
  public Customer delete(@PathVariable Integer id) {
    return customerService.delete(id);
  }



}