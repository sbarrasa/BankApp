package com.sbarrasa.bank.service;

import com.sbarrasa.bank.controller.dto.CustomerDTO;
import com.sbarrasa.bank.model.customer.CustomerEntity;
import com.sbarrasa.bank.model.customer.Gender;
import com.sbarrasa.bank.repository.CustomerRepository;
import com.sbarrasa.bank.service.exceptions.CustomerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private CustomerService customerService;

  private CustomerDTO customerDTO;
  private CustomerEntity customerEntity;

  @BeforeEach
  void setUp() {
    customerDTO = new CustomerDTO();
    customerDTO.setId(1);
    customerDTO.setFirstName("Sebastián");
    customerDTO.setLastName("Barrasa");
    customerDTO.setGender(Gender.M);

    customerEntity = new CustomerEntity();
    customerEntity.setId(1);
    customerEntity.setFirstName("Sebastián");
    customerEntity.setLastName("Barrasa");
    customerEntity.setGender(Gender.M);

  }

  @Test
  void create_successful() {
    when(customerRepository.existsById(customerDTO.getId())).thenReturn(false);
    when(customerRepository.save(customerEntity)).thenReturn(customerEntity);

    var result = customerService.create(customerDTO);

    assertNotNull(result);
    assertEquals(customerDTO.getId(), result.getId());
    assertEquals(customerDTO.getFirstName(), result.getFirstName());

    verify(customerRepository, times(1)).existsById(customerDTO.getId());
    verify(customerRepository, times(1)).save(customerEntity);
  }

  @Test
  void create_throwsException_whenCustomerExists() {
    when(customerRepository.existsById(customerDTO.getId())).thenReturn(true);

    assertThrows(CustomerException.class, () -> customerService.create(customerDTO));

    verify(customerRepository, times(1)).existsById(customerDTO.getId());
    verify(customerRepository, never()).save(any());
  }

  @Test
  void save_successful() {
    when(customerRepository.save(customerEntity)).thenReturn(customerEntity);

    var result = customerService.save(customerEntity);

    assertNotNull(result);
    assertEquals(customerDTO.getId(), result.getId());

    verify(customerRepository, times(1)).save(customerEntity);
  }

  @Test
  void update_successful() {
    when(customerRepository.findById(customerDTO.getId())).thenReturn(Optional.of(customerEntity));
    when(customerRepository.save(customerEntity)).thenReturn(customerEntity);

    var result = customerService.update(customerDTO.getId(), customerDTO);

    assertNotNull(result);
    assertEquals(customerDTO.getId(), result.getId());

    verify(customerRepository, times(1)).findById(customerDTO.getId());
    verify(customerRepository, times(1)).save(customerEntity);
  }

  @Test
  void update_throwsException_whenCustomerNotFound() {
    when(customerRepository.findById(customerDTO.getId())).thenReturn(Optional.empty());

    assertThrows(CustomerException.class, () -> customerService.update(customerDTO.getId(), customerDTO));

    verify(customerRepository, times(1)).findById(customerDTO.getId());
    verify(customerRepository, never()).save(any());
  }

  @Test
  void delete_successful() {
    when(customerRepository.findById(customerDTO.getId())).thenReturn(Optional.of(customerEntity));

    var result = customerService.delete(customerDTO.getId());

    assertNotNull(result);
    assertEquals(customerDTO.getId(), result.getId());

    verify(customerRepository, times(1)).findById(customerDTO.getId());
    verify(customerRepository, times(1)).deleteById(customerDTO.getId());
  }

  @Test
  void delete_throwsException_whenCustomerNotFound() {
    when(customerRepository.findById(customerDTO.getId())).thenReturn(Optional.empty());

    assertThrows(CustomerException.class, () -> customerService.delete(customerDTO.getId()));

    verify(customerRepository, times(1)).findById(customerDTO.getId());
    verify(customerRepository, never()).deleteById(any());
  }

  @Test
  void getAll_successful() {
    when(customerRepository.findAll()).thenReturn(List.of(customerEntity));

    var customers = customerService.getAll();

    assertNotNull(customers);
    assertFalse(customers.isEmpty());
    assertEquals(1, customers.size());

    verify(customerRepository, times(1)).findAll();
  }

  @Test
  void findCustomer_successful() {
    when(customerRepository.findById(customerDTO.getId())).thenReturn(Optional.of(customerEntity));

    CustomerEntity result = customerService.findCustomer(customerDTO.getId());

    assertNotNull(result);
    assertEquals(customerEntity.getId(), result.getId());

    verify(customerRepository, times(1)).findById(customerDTO.getId());
  }

  @Test
  void findCustomer_throwsException_whenNotFound() {
    when(customerRepository.findById(customerDTO.getId())).thenReturn(Optional.empty());

    assertThrows(CustomerException.class, () -> customerService.findCustomer(customerDTO.getId()));

    verify(customerRepository, times(1)).findById(customerDTO.getId());
  }

  @Test
  void getCustomer_successful() {
    when(customerRepository.findById(customerDTO.getId())).thenReturn(Optional.of(customerEntity));

    var result = customerService.getCustomer(customerDTO.getId());

    assertNotNull(result);
    assertEquals(customerDTO.getId(), result.getId());

    verify(customerRepository, times(1)).findById(customerDTO.getId());
  }
}