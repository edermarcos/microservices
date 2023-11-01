package com.edermarcos.productsservice.services;

import com.edermarcos.productsservice.model.dtos.ProductRequest;
import com.edermarcos.productsservice.model.dtos.ProductResponse;
import com.edermarcos.productsservice.model.entities.ProductEntity;
import com.edermarcos.productsservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  public void create(ProductRequest productRequest) {
    var product = ProductEntity.builder()
        .sku(productRequest.getSku())
        .name(productRequest.getName())
        .description(productRequest.getDescription())
        .price(productRequest.getPrice())
        .status(productRequest.getStatus())
        .build();

    productRepository.save(product);
  }

  public List<ProductResponse> getAll() {
    var products = productRepository.findAll();
    return products.stream().map(this::mapToProductEntity).toList();
  }

  private ProductResponse mapToProductEntity(ProductEntity productEntity) {
    return ProductResponse.builder()
        .sku(productEntity.getSku())
        .name(productEntity.getName())
        .description(productEntity.getDescription())
        .price(productEntity.getPrice())
        .status(productEntity.getStatus())
        .build();
  }
}
