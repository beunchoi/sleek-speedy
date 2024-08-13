package com.hanghae.sleekspeedy.domain.basket.dto;

import com.hanghae.sleekspeedy.domain.basket.entity.Basket;
import com.hanghae.sleekspeedy.domain.basket.entity.BasketProduct;
import com.hanghae.sleekspeedy.domain.product.entity.Product;
import lombok.Getter;

@Getter
public class BasketProductResponse {

  private Basket basket;
  private Product product;
  private int count;

  public BasketProductResponse(BasketProduct basketProduct) {
    this.basket = basketProduct.getBasket();
    this.product = basketProduct.getProduct();
    this.count = basketProduct.getCount();
  }
}
