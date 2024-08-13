package com.hanghae.sleekspeedy.domain.product.controller;

import com.hanghae.sleekspeedy.domain.product.dto.ProductRequestDto;
import com.hanghae.sleekspeedy.domain.product.dto.ProductResponseDto;
import com.hanghae.sleekspeedy.domain.product.service.ProductService;
import com.hanghae.sleekspeedy.global.response.ResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping("/products")
  public ResponseEntity<ResponseDto> createProduct(@RequestBody ProductRequestDto requestDto) {
    productService.createProduct(requestDto);
    return ResponseEntity.ok().body(ResponseDto.success(200));
  }

  @GetMapping("/products")
  public List<ProductResponseDto> getProducts() {
    return productService.getProducts();
  }

  @GetMapping("/products/{productId}")
  public ProductResponseDto getProductDescription(@PathVariable Long productId) {
    return productService.getProductDescription(productId);
  }
}
