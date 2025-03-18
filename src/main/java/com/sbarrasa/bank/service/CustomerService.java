package com.sbarrasa.bank.service;

import com.sbarrasa.bank.customer.Customer;
import com.sbarrasa.bank.customer.CustomerDTO;
import com.sbarrasa.bank.customer.CustomerEntity;
import com.sbarrasa.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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


  public CustomerEntity get(Integer id) {
    return customerRepository.findById(id)
      .orElseThrow(() -> new CustomerNotFoundException(id));
  }

  public CustomerEntity create(CustomerEntity customer) {
    if(customerRepository.existsById(customer.getId()))
      throw new DuplicatedCustomerException(customer.getId());

    return customerRepository.save(customer);

  }

  public CustomerEntity update(CustomerEntity customer) {
    get(customer.getId());
    return customerRepository.save(customer);
  }

  public CustomerEntity delete(Integer id) {
    var customer= this.get(id);

    /*arma una copia del customer que se va a borrar
    y le actualiza la fecha de ultima actualización
    para devolverlo en caso de desear mostrar la info del customer eliminado
     */
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
}
