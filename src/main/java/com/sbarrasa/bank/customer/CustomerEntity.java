package com.sbarrasa.bank.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sbarrasa.bank.product.Product;
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
@Table(name = "CUSTOMER")
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public class CustomerEntity implements Customer {

  @Id
  private Integer id;

  @Column(length = 30)
  private String firstName;

  @Column(length = 30)
  private String lastName;

  @Column(length = 1)
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Embedded
  private Address address;

  @Email
  @Column(length = 40)
  private String email;

  @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
  @Column(length = 20)
  private String phoneNumber;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createDate;

  @LastModifiedDate
  @Column(nullable = false)
  private LocalDateTime lastUpdate;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "customer_id")
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