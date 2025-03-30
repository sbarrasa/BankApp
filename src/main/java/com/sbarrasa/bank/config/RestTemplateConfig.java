package com.sbarrasa.bank.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateConfig {
  @Value("${bank.api.customers.url}")
  private String customersApiUrl;

  @Bean("customerRestService")
  public RestTemplate customerRestService() {
    var factory = new DefaultUriBuilderFactory(customersApiUrl);
    var  restTemplate = new RestTemplate();
    restTemplate.setUriTemplateHandler(factory);
    return restTemplate;
  }

}
