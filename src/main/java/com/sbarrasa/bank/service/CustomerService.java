package com.sbarrasa.bank.service;

import com.sbarrasa.bank.customer.Customer;
import com.sbarrasa.bank.customer.CustomerDTO;
import com.sbarrasa.bank.model.CustomerEntity;
import com.sbarrasa.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public List<Customer> getAll() {
      return customerRepository.findAll().stream()
        .map(CustomerDTO::new)
        .collect(Collectors.toList());
  }

  public Customer get(Integer id) {
    return customerRepository.findById(id)
      .orElseThrow(() -> new CustomerNotFoundException(id));
  }

  public Customer create(CustomerEntity customer) {
    if(customerRepository.existsById(customer.getId()))
      throw new ExistingCustomerException(customer.getId());

    return customerRepository.save(customer);

  }

  public Customer update(CustomerEntity customer) {
    get(customer.getId());
    return customerRepository.save(customer);
  }

  public Customer delete(Integer id) {
    var customer= this.get(id);
    var customerDeleted = new CustomerEntity(customer);
    customerDeleted.setLastUpdate(LocalDateTime.now());

    customerRepository.deleteById(id);
    return customerDeleted;
  }


}
