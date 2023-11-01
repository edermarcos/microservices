package com.edermarcos.productsservice.controllers;

import com.edermarcos.productsservice.model.dtos.ProductRequest;
import com.edermarcos.productsservice.model.dtos.ProductResponse;
import com.edermarcos.productsservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody ProductRequest productRequest) {
    productService.create(productRequest);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductResponse> getAll() {
    return productService.getAll();
  }
}
