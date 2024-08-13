package com.hanghae.sleekspeedy.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hanghae.sleekspeedy.domain.product.entity.Product;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponseDto {

  private String title;
  private String image;
  private Integer price;
  private String category;
  private String description;

  public ProductResponseDto(Product product) {
    this.title = product.getTitle();
    this.image = product.getImage();
    this.price = product.getPrice();
  }

  public ProductResponseDto(String description) {
    this.description = description;
  }
}
