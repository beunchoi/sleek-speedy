package com.hanghae.sleekspeedy.domain.basketProduct.service;

import com.hanghae.sleekspeedy.domain.basketProduct.repository.BasketProductRepository;
import com.hanghae.sleekspeedy.domain.basketProduct.entity.BasketProduct;
import com.hanghae.sleekspeedy.domain.product.entity.Product;
import com.hanghae.sleekspeedy.domain.product.repository.ProductRepository;
import com.hanghae.sleekspeedy.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketProductService {

  private final BasketProductRepository basketProductRepository;
  private final ProductRepository productRepository;

  public void addProductToBasket(User user, Long productId) {

    Product product = productRepository.findById(productId).orElseThrow(() -> new NullPointerException("해당 상품이 존재하지 않습니다."));
    BasketProduct basketProduct = new BasketProduct(user, product);

    basketProductRepository.save(basketProduct);
  }
}
