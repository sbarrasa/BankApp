package com.sbarrasa.bank.service;

import com.sbarrasa.bank.controller.dto.CustomerDTO;
import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.bank.model.customer.CustomerEntity;
import com.sbarrasa.bank.model.product.ProductEntity;
import com.sbarrasa.bank.service.exceptions.CustomerProductException;
import com.sbarrasa.util.matcher.MatchType;
import com.sbarrasa.util.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerProductsService {
  private final ProductMatcher matcher;
  private final ProductAdapter adapter;
  private final Validator validator;

  @Autowired
  public CustomerProductsService(ProductMatcher matcher, ProductAdapter adapter) {
    this.matcher = matcher;
    this.adapter = adapter;
    this.validator = new Validator();
  }


  public Set<ProductDTO> filter(CustomerEntity customer, ProductDTO newProduct) {
    var filteredProducts = customer.getProducts().stream()
      .filter(product -> matcher.match(product, newProduct, MatchType.ALL))
      .collect(Collectors.toSet());

    return adapter.toDTOSet(filteredProducts);
  }

  public ProductEntity find(CustomerEntity customer, ProductDTO sampleProduct) {

    return customer.getProducts().stream()
      .filter(product -> matcher.match(product, sampleProduct, MatchType.ALL))
      .findFirst().orElse(null);
  }

  private void verifyExists(CustomerEntity customer, ProductDTO sampleProduct) {
    var product = find(customer, sampleProduct);
    if (product != null)
      throw new CustomerProductException(new CustomerDTO(customer), adapter.toDTO(product), CustomerProductException.DUPLICATED);
  }

  private void verifyCBU(CustomerEntity customer, ProductDTO newProduct) {
    if (newProduct.getCbu() != null) {
      var account = new ProductDTO().setCbu(newProduct.getCbu());
      verifyExists(customer, account);
    }

  }

  @Transactional
  public Set<ProductDTO> update(CustomerEntity customer, ProductDTO productSample, ProductDTO updateProduct) {
    if (filter(customer, productSample).isEmpty())
      throw new CustomerProductException(new CustomerDTO(customer), productSample, CustomerProductException.NOT_FOUND);

    var updatedProducts = customer.getProducts().stream()
      .filter(currentProduct -> matcher.match(currentProduct, productSample, MatchType.ALL))
      .map(currentProduct -> adapter.map(updateProduct, currentProduct))
      .collect(Collectors.toSet());

    return adapter.toDTOSet(updatedProducts);
  }

  @Transactional
  public Set<ProductDTO> delete(CustomerEntity customer, ProductDTO productSample) {
    var productsToDelete = filter(customer, productSample);
    if (productsToDelete.isEmpty())
      throw new CustomerProductException(new CustomerDTO(customer),
        productSample,
        CustomerProductException.NOT_FOUND);

    customer.getProducts()
      .removeIf(product ->
        matcher.match(product, productSample, MatchType.ALL)
      );

    return productsToDelete;
  }

  @Transactional
  public ProductDTO add(CustomerEntity customer, ProductDTO newProduct) {
    validator.validate(newProduct);
    verifyExists(customer, adapter.cleanDTO(newProduct));
    verifyCBU(customer, newProduct);

    var productEntity = adapter.toEntity(newProduct);
    validator.validate(productEntity);


    customer.getProducts().add(productEntity);

    return adapter.toDTO(productEntity);
  }


}
