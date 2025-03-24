package com.sbarrasa.bank.service;

import com.sbarrasa.bank.model.product.ProductEntity;
import com.sbarrasa.bank.service.exceptions.ProductFactoryException;
import com.sbarrasa.util.id.IdDesc;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Getter
@Service
public class ProductFactory  {
  private final Set<Descriptor> descriptors = new HashSet<>();
  public final static String INVALID_PRODUCT_TYPE = "valores posibles %s";
  public final static String NO_PRODCUTS_REGISTERED = "no hay productos registrados";

  public ProductFactory register(Supplier<? extends ProductEntity> productSupplier){
    var product = productSupplier.get();
    var descriptor = new Descriptor(product.getProductType(), product.getName(), productSupplier);
    register(descriptor);
    return this;
  }

  public ProductFactory register(Descriptor descriptor){
    descriptors.add(descriptor);
    return this;
  }

  @SuppressWarnings("unchecked")
  public <T extends ProductEntity> T create(String productType) {
    if(descriptors.isEmpty())
      throw new ProductFactoryException(NO_PRODCUTS_REGISTERED);

    return descriptors.stream()
      .filter(descriptor -> descriptor.id.equals(productType))
      .map(descriptor -> (T) descriptor.supplier.get())
      .findFirst()
      .orElseThrow(() -> new ProductFactoryException(INVALID_PRODUCT_TYPE.formatted(getIds())));
  }

  public Set<IdDesc<String>> getAllDescriptors() {
      return descriptors.stream()
        .map(descriptor -> IdDesc.of(descriptor.id, descriptor.name))
        .collect(Collectors.toSet());
  }

  public Set<String> getIds() {
    return descriptors.stream()
      .map(descriptor -> descriptor.id)
      .collect(Collectors.toSet());
  }

  public record Descriptor(String id, String name, Supplier<? extends ProductEntity> supplier){}
}
