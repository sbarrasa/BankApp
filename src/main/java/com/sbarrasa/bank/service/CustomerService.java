package com.sbarrasa.bank.service;

import com.sbarrasa.bank.controller.dto.CustomerDTO;
import com.sbarrasa.bank.model.customer.Customer;
import com.sbarrasa.bank.model.customer.CustomerEntity;
import com.sbarrasa.bank.repository.CustomerRepository;
import com.sbarrasa.bank.service.exceptions.CustomerNotFoundException;
import com.sbarrasa.bank.service.exceptions.DuplicatedCustomerException;
import com.sbarrasa.util.validator.Validator;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public List<Customer> getAll() {
      return customerRepository.findAll().stream()
        .map(CustomerDTO::new)
        .collect(Collectors.toList());
  }



  @Transactional
  public CustomerEntity create(CustomerEntity customer) {
    if( customerRepository.existsById(customer.getId()))
      throw new DuplicatedCustomerException(new CustomerDTO(customer));

    Validator.validate(customer);

    return customerRepository.save(customer);

  }

  @Transactional
  public CustomerEntity update(CustomerEntity customer) {
    if(get(customer.getId())==null)
      throw new CustomerNotFoundException(new CustomerDTO(customer));

    Validator.validate(customer);

    return customerRepository.save(customer);
  }

  @Transactional
  public CustomerEntity delete(Integer id) {
    var customer = get(id);
    if(customer == null)
      throw new CustomerNotFoundException(new CustomerDTO().setId(id));


    /* arma copia de customer a borrar
    y actualiza lastUpdate para mostrar customer eliminado */

    var customerDeleted = new CustomerEntity(customer)
                                              .setLastUpdate(LocalDateTime.now());

    customerRepository.deleteById(id);
    return customerDeleted;
  }

  public List<Customer> filter(CustomerEntity customerExample) {
    Example<CustomerEntity> example = Example.of(customerExample);

    return customerRepository.findAll(example).stream()
      .map(customer -> (Customer)new CustomerDTO(customer))
      .toList();
  }


  public CustomerEntity get(Integer id) {
    return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(new CustomerDTO().setId(id)));
  }
}
