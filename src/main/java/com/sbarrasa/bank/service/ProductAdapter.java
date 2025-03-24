package com.sbarrasa.bank.service;

import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.bank.model.product.ProductEntity;
import com.sbarrasa.util.validator.Validator;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductAdapter {

  private final ModelMapper modelMapper;
  private final Validator validator;

  @Getter
  private final ProductFactory productFactory;

  public ProductAdapter() {
    this(new ProductFactory());
  }

  @Autowired
  public ProductAdapter(ProductFactory productFactory) {
    this.validator = new Validator();
    this.productFactory = productFactory;
    modelMapper = buildModelMapper();
  }

  public Set<ProductDTO> toDTOSet(Set<ProductEntity> productEntitieSet) {
    return productEntitieSet.stream()
      .map(this::toDTO)
      .collect(Collectors.toSet());
  }

  @SuppressWarnings("unchecked")
  public <T extends ProductEntity> T toEntity(ProductDTO productDTO) {
    validator.validate(productDTO);
    
    var productEntity = productFactory.create(productDTO.getProductType());
    modelMapper.map(productDTO, productEntity);

    validator.validate(productEntity);
    return (T) productEntity;
  }


  public ProductDTO toDTO(ProductEntity productEntity) {
    var productDTO = new ProductDTO();

    modelMapper.map(productEntity, productDTO);
    return productDTO;
  }

  public ProductDTO cleanDTO(ProductDTO sampleProduct) {
    return toDTO(toEntity(sampleProduct));

  }


  public ProductDTO map(ProductEntity source, ProductDTO target) {
    modelMapper.map(source, target);
    return target;
  }
  
  public ProductEntity map(ProductDTO source, ProductEntity target) {
    modelMapper.map(source, target);
    return target;
  }

  private ModelMapper buildModelMapper(){
    var mapper = new ModelMapper();
    mapper.getConfiguration().setSkipNullEnabled(true);

    mapper.addMappings(new PropertyMap<ProductEntity, ProductDTO>() {
      @Override
      protected void configure() {
        map(false, destination.getIsCredit());
      }
    });

    return mapper;
  }

}
