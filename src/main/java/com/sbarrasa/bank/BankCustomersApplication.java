package com.sbarrasa.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BankCustomersApplication {

  public static void main(String[] args) {
    SpringApplication.run(BankCustomersApplication.class, args);
  }

}
