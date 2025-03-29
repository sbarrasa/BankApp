package com.sbarrasa.bank.model.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sbarrasa.bank.model.product.ProductEntity;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "customers")
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public class CustomerEntity implements Customer {

  @Id
  private Integer id;

  @Column(length = 30)
  @NotNull
  private String firstName;

  @Column(length = 30)
  @NotNull
  private String lastName;

  @Column(length = 1)
  @Enumerated(EnumType.STRING)
  @NotNull
  private Gender gender;

  @Valid
  @Embedded
  private Address address;

  @Email
  @Column(length = 40)
  private String email;

  @Pattern(regexp = "^[0-9]{10}$", message = "Teléfono inválido")
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
  private Set<ProductEntity> products;


}