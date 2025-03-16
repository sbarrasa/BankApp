package com.sbarrasa.bank.model;

import com.sbarrasa.bank.customer.Address;
import com.sbarrasa.bank.customer.Customer;
import com.sbarrasa.bank.customer.Gender;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Data
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
public class CustomerEntity implements Customer {

  @Id
  private Integer id;

  private String firstName;

  private String lastName;

  private Gender gender;

  @Embedded
  private Address address;

  @Email
  private String email;

  @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
  private String phoneNumber;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createDate;

  @LastModifiedDate
  @Column(nullable = false)
  private LocalDateTime lastUpdate;

  @ManyToMany
  @JoinTable(
    name = "customer_product",
    joinColumns = @JoinColumn(name = "customer_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
  private Set<Product> products;

  public CustomerEntity(){

  }

  public CustomerEntity(Customer customer){
    this.setId(customer.getId());
    this.setFirstName(customer.getFirstName());
    this.setLastName(customer.getLastName());
    this.setGender(customer.getGender());
    this.setEmail(customer.getEmail());
    this.setAddress(customer.getAddress());
    this.setPhoneNumber(customer.getPhoneNumber());
  }


}