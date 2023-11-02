package com.edermarcos.productsservice.controllers;

import com.edermarcos.productsservice.model.dtos.ProductRequest;
import com.edermarcos.productsservice.model.entities.ProductEntity;
import com.edermarcos.productsservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductEntity create(@RequestBody ProductRequest productRequest) {
    return productService.save(productRequest);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductEntity> getAll() {
    return productService.getAll();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ProductEntity> getById(@PathVariable Long id) {
    return productService.getById(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductEntity> update(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
    return productService.update(id, productRequest);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    return productService.delete(id);
  }
}
