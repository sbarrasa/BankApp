package com.sbarrasa.bank.view;

import com.sbarrasa.bank.controller.dto.CustomerDTO;
import com.sbarrasa.bank.controller.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
public class CustomerViewController {


  private final RestTemplate restService;

  @Autowired
  public CustomerViewController(@Qualifier("customerRestService") RestTemplate customerRestService){
    this.restService = customerRestService;
  }


  @GetMapping("/view/customers/{id}")
  public String getCustomer(@PathVariable("id") Long id, Model model) {
    var customer = restService.getForObject("{id}", CustomerDTO.class, id);
    var products = restService.getForObject("{id}/products", ProductDTO[].class, id);

    model.addAttribute("customer", customer);
    model.addAttribute("products", products);
    return "customer";
  }

  @ExceptionHandler(HttpClientErrorException.class)
  public ModelAndView handleException(HttpClientErrorException ex) {
    var modelAndView = new ModelAndView("error");
    modelAndView.addObject("error", ex.getStatusCode());
    modelAndView.addObject("status", ex.getStatusCode());
    modelAndView.addObject("message", ex.getMessage());
    modelAndView.addObject("exception", ex);
    modelAndView.addObject("timestamp", LocalDateTime.now());


    return modelAndView;
  }
}