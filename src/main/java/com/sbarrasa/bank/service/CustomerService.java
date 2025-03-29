package com.sbarrasa.bank.service;

import com.sbarrasa.bank.controller.dto.CustomerDTO;
import com.sbarrasa.bank.model.customer.Customer;
import com.sbarrasa.bank.model.customer.CustomerEntity;
import com.sbarrasa.bank.repository.CustomerRepository;
import com.sbarrasa.bank.service.exceptions.CustomerException;
import com.sbarrasa.util.validator.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final Validator validator;
  private final ModelMapper mapper;


  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
    this.mapper = createModelMapper();
    this.validator = new Validator();
  }

  private ModelMapper createModelMapper() {
    var mapper = new ModelMapper();
    mapper.getConfiguration().setSkipNullEnabled(true);
    return mapper;
  }


  @Transactional
  public Customer create(CustomerDTO customer) {
    if (customerRepository.existsById(customer.getId()))
      throw new CustomerException(customer, CustomerException.DUPLICATED);

    var customerEntity = mapper.map(customer, CustomerEntity.class);

    return save(customerEntity);
  }

  @Transactional
  public Customer save(CustomerEntity customer) {
    validator.validate(customer);

    return mapper.map(customerRepository.save(customer), CustomerDTO.class);

  }

  @Transactional
  public Customer update(Integer customerId, Customer customer) {
    var customerEntity = findCustomer(customerId);

    if (customerEntity == null)
      throw new CustomerException(customer, CustomerException.NOT_FOUND);

    mapper.map(customer, customerEntity);
    return save(customerEntity);

  }

  @Transactional
  public Customer delete(Integer id) {
    var customer = getCustomer(id);
    if (customer == null)
      throw new CustomerException(new CustomerDTO().setId(id), CustomerException.NOT_FOUND);

    // se devolverá una copia del customer borrado con el lastUpdate de la eliminación
    var customerDeleted = mapper.map(customer, CustomerDTO.class);
    customerDeleted.setLastUpdate(LocalDateTime.now());

    customerRepository.deleteById(id);
    return customerDeleted;
  }

  public List<Customer> getAll() {
    return customerRepository.findAll().stream()
      .map(customer -> mapper.map(customer, CustomerDTO.class))
      .collect(Collectors.toList());
  }


  public List<Customer> filter(Customer customerExample) {
    Example<CustomerEntity> example = Example.of(
      mapper.map(customerExample, CustomerEntity.class));

    return customerRepository.findAll(example).stream()
      .map(customer -> mapper.map(customer, CustomerDTO.class))
      .collect(Collectors.toList());
  }

  public CustomerEntity findCustomer(Integer id) {
    return customerRepository.findById(id).orElseThrow(
      () -> new CustomerException(new CustomerDTO().setId(id), CustomerException.NOT_FOUND)
    );
  }

  public Customer getCustomer(Integer id) {
    return mapper.map(findCustomer(id), CustomerDTO.class);
  }
}
