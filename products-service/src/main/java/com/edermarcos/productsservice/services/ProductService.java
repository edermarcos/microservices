package com.edermarcos.productsservice.services;

import com.edermarcos.productsservice.model.dtos.ProductRequest;
import com.edermarcos.productsservice.model.entities.ProductEntity;
import com.edermarcos.productsservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  public ProductEntity save(ProductRequest productRequest) {
    var product = getProductEntity(productRequest);
    return productRepository.save(product);
  }

  public List<ProductEntity> getAll() {
    return productRepository.findAll();
  }

  public Optional<ProductEntity> getById2(Long id) {
    return productRepository.findById(id);
  }

  public ResponseEntity<ProductEntity> getById(Long id) {
    return productRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  public ResponseEntity<ProductEntity> update(Long id, ProductRequest productRequest) {
    return productRepository.findById(id).map(savedProduct -> {
      savedProduct.setSku(productRequest.getSku());
      savedProduct.setName(productRequest.getName());
      savedProduct.setDescription(productRequest.getDescription());
      savedProduct.setPrice(productRequest.getPrice());
      savedProduct.setStatus(productRequest.getStatus());

      productRepository.save(savedProduct);
      return ResponseEntity.ok(savedProduct);
    }).orElse(ResponseEntity.notFound().build());
  }

  public ResponseEntity<Void> delete(Long id) {
    return productRepository.findById(id).map(savedProduct -> {
      productRepository.delete(savedProduct);
      return ResponseEntity.ok().<Void>build();
    }).orElse(ResponseEntity.notFound().build());
  }

  private ProductEntity getProductEntity(ProductRequest productRequest) {
    return ProductEntity.builder().sku(productRequest.getSku()).name(productRequest.getName()).description(productRequest.getDescription()).price(productRequest.getPrice()).status(productRequest.getStatus()).build();
  }
}
